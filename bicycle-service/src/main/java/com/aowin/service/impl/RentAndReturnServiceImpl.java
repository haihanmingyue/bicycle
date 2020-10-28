package com.aowin.service.impl;

import com.aowin.dao.*;
import com.aowin.model.BicycleInfo;
import com.aowin.model.BicycleRecord;
import com.aowin.model.Card;
import com.aowin.service.RentAndReturnService;
import com.aowin.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import sun.security.x509.RFC822Name;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class RentAndReturnServiceImpl implements RentAndReturnService {

	final
	BicycleInfoMapper bicycleInfoMapper;
	final
	CardMapper cardMapper;
	final
	BicyclePileMapper bicyclePileMapper;
	final
	CardRecordMapper recordMapper;
	final
	BicycleRecordMapper bicycleRecordMapper;
	final
	BicycleDealMapper bicycleDealMapper;

	public RentAndReturnServiceImpl(BicycleDealMapper bicycleDealMapper,BicycleRecordMapper bicycleRecordMapper,
									BicycleInfoMapper bicycleInfoMapper,CardMapper cardMapper,
									BicyclePileMapper bicyclePileMapper,CardRecordMapper recordMapper) {
		this.bicycleInfoMapper = bicycleInfoMapper;
		this.cardMapper = cardMapper;
		this.bicyclePileMapper = bicyclePileMapper;
		this.recordMapper = recordMapper;
		this.bicycleRecordMapper = bicycleRecordMapper;
		this.bicycleDealMapper = bicycleDealMapper;

	}

	@Override
	@Transactional
	public int RentBicycle(Map<String,Object> map) {
		String card_code = (String) map.get("card_code");
		Card card =  cardMapper.getUserCardMsg(card_code);
		map.put("card_id",card.getCard_id());
		int i = bicycleInfoMapper.updateBicycleInfo(map);//	修改车辆状态表，将车辆状态改成（2：借出），清空所在车桩id，租车卡号填写租车卡id
		System.out.println("i: "+ i );

		Integer pile_id = (Integer) map.get("pile_id");
		Map<String,Object> map1 = new HashMap<>();
		map1.put("bicycle_id",null);
		map1.put("status",2);
		map1.put("pile_id",pile_id);
		int j =  bicyclePileMapper.rentAndReturnUpdate(map1);  //3)	修改车辆车桩表，将车桩状态改成（2：无车），清空所存车辆的id。


		Map<String,Object> map2 = new HashMap<>();
//		a)	如果冻结金额为0，则将余额划拨200元到冻结金额中。
//		b)	如果冻结金额不够200元，则划拨200-实际冻结金额到冻结金额中。
//		c)	如果冻结金额为200元，则不划拨。
		int k;
		int l;
		Map<String,Object> map3 = new HashMap<>();
		Date d = new Date();
		if(card.getFrozen_money() <= 0){
			map2.put("money1",200);
			map2.put("money2",200);
			map2.put("card_code",card_code);
			k = cardMapper.rentUpdateCard(map2);
			System.out.println("k: "+k );
			map3.put("card_id",card.getCard_id());
			map3.put("fee_type",3);
			map3.put("chg_wallet_money",200);
			map3.put("chg_frozen_money",-200);
			map3.put("create_time", TimeUtil.dateToStr2(d));
			//)	若有冻结金额变动 需要将变动信息记录在卡费用流水表中
			l = recordMapper.rentInsertCardRecord(map3);
			System.out.println("l: "+l );
		}else if(card.getFrozen_money()>0 && card.getFrozen_money() <200){
			map2.put("money1",200-card.getFrozen_money());
			map2.put("money2",200-card.getFrozen_money());
			map2.put("card_code",card_code);
			k = cardMapper.rentUpdateCard(map2);
			System.out.println("k: "+k );
			map3.put("card_id",card.getCard_id());
			map3.put("fee_type",3);
			map3.put("chg_wallet_money",0-(200-card.getFrozen_money()));
			map3.put("chg_frozen_money",200-card.getFrozen_money());
			map3.put("create_time", TimeUtil.dateToStr2(d));
			//d)	若有冻结金额变动 需要将变动信息记录在卡费用流水表中
			l = recordMapper.rentInsertCardRecord(map3);
			System.out.println("l: "+l );
		}else {
			k = 1;  //不执行的话就默认1
			l = 1;
		}
		//5)	需要在车辆租还记录表中增加一条租车记录。
		Integer record_id;
		Random random = new Random();
		Map<String,Object> map4= new HashMap<>();
		Map<String,Object> map5= new HashMap<>();
		boolean temp = true;
		while (temp){
			record_id = random.nextInt(2147483647);
			if(bicycleRecordMapper.selectById(record_id)==0){
				map4.put("record_id",record_id);
				map5.put("record_id",record_id);
				temp = false;
			}
		}
		Integer bicycle_id = (Integer) map.get("bicycle_id");
		map4.put("bicycle_id",bicycle_id);
		map4.put("card_id",card.getCard_id());
		map4.put("rent_time",TimeUtil.dateToStr2(d));
		map4.put("rent_pile_id",pile_id);
		int m = bicycleRecordMapper.rentAndReturnInsert(map4);

//		6)	记录车辆业务流水，业务类型为（2：租车）关联的业务记录id为车辆租还记录id，
//		业务名称为（租出），业务卡号为租车卡号，是否发生费用为（0：未发生）。
		map5.put("create_time",TimeUtil.dateToStr2(d));
		map5.put("deal_name","租出");
		map5.put("deal_type",2);
		map5.put("card_id",card.getCard_id());
		map5.put("is_fee",0);
		map5.put("chg_money",null);
		map5.put("fee_type",null);
		int n = bicycleDealMapper.rentAndReturnUpdate(map5);

		if((i+j+k+l+m+n) != 6){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 99;
		}else {
			return 1;
		}

	}


	@Override
	@Transactional
	public int ReturnBicycle(Map<String,Object> map) {

//		3)	计算租车费用（当前时间-租车时间，1个小时之内免费，
//		1-2个小时为1元/小时，2-3个小时为2元/小时，
//		3个小时以上为3元/小时。不足一小时按一小时计算）。
		String card_code = (String) map.get("card_code");
		Card card =  cardMapper.getUserCardMsg(card_code);
		Integer card_id = card.getCard_id();//先获取card_id 去查询租借记录 找到没有归还时间的那一条记录
		BicycleRecord br = bicycleRecordMapper.selectByCardId(card_id);//得到BicycleRecord 对象，里面存着 自行车id 和 租用时间
		long currentTime =System.currentTimeMillis();//系统当前时间
		long rent_time = 0;
		try {
			rent_time = TimeUtil.Str2ToDate(br.getRent_time()).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff=(currentTime-rent_time)/1000/60;
		System.out.println("相差时间："+diff);
		double feiyong;//
		double hourMoney;//每小时费用
		if(diff>=0 && diff<60){
			feiyong = 0;
			hourMoney = 0;
		}else if(diff >=60 && diff <=120){
			feiyong = 2.0;
			hourMoney = 1.0;
		}else if(diff >120 && diff < 180){
			feiyong = 6.0;
			hourMoney = 2.0;
		}else {
			long hour = diff/60;
			System.out.println("是否整除"+(diff%60 != 0));
			if(diff%60 != 0){
				hour += 1;
			}
			System.out.println(hour);
			hourMoney = 3.0;
			feiyong = hour*hourMoney;

		}
//		4)	校验卡余额是否有余额还车（卡内余额-租车费用>=0）
		if(card.getWallet_money()-feiyong<=0){
			return 98; //2 费用不足
		}

		Date d = new Date();
		//修改卡内余额，扣除租车费用，写入卡内余额，
		Map<String,Object> map2 = new HashMap<>();
		map2.put("money1",0);
		map2.put("money2",feiyong);
		map2.put("card_code",card_code);
		int k = cardMapper.rentUpdateCard(map2);
		System.out.println("k "+k);
		if(feiyong == 0){
			k = 1; //没有费用时，update由于数据实际上没有更新，的返回值是0
		}

	//	并且添加卡费用流水信息。费用类型为（3：租车）。
		Map<String,Object> map3 = new HashMap<>();
		map3.put("card_id",card.getCard_id());
		map3.put("fee_type",3);
		map3.put("chg_wallet_money",-feiyong);
		map3.put("chg_frozen_money",null);
		map3.put("create_time", TimeUtil.dateToStr2(d));
		int l = recordMapper.rentInsertCardRecord(map3);
		System.out.println("l "+l);

		Integer pile_id = (Integer) map.get("pile_id");
		//修改车辆信息表，状态改为（3：在桩），清空租车卡id，修改车辆所在车桩的id为还车的车桩id。
		Map<String,Object> map4 = new HashMap<>();
		map4.put("pile_id",pile_id);
		map4.put("bicycle_id",br.getBicycle_id());
		int c = bicycleInfoMapper.updateReturnBicycleInfo(map4);
		System.out.println("c "+c);
		//修改车桩表，将车桩的状态改成（1：有车），修改所存车辆id为还车的车辆id。
		Map<String,Object> map1 = new HashMap<>();
		map1.put("bicycle_id",br.getBicycle_id());
		map1.put("status",1);
		map1.put("pile_id",pile_id);
		int j =  bicyclePileMapper.rentAndReturnUpdate(map1);
		System.out.println("j "+j);
//		9)	修改车辆租还记录表，补充其还车环节。并将租车佣金写入其中。
//		update bicycle_record set return_time = #{return_time},
//		return_pile_id = #{return_pile_id},money = #{money} where
//		record_id = #{record_id}
		Map<String,Object> map6= new HashMap<>();
		map6.put("return_time",TimeUtil.dateToStr2(d));
		map6.put("return_pile_id",pile_id);
		map6.put("money",feiyong);
		map6.put("record_id",br.getRecord_id());
		int y = bicycleRecordMapper.update(map6);
		System.out.println("y "+y);
//		10)	写业务流水表。
//		a)	业务类型为（3：还入）
//		b)	关联的业务记录id为车辆租还记录id，
//		c)	业务名称为（还入），
//		d)	业务卡id为还车卡id,
//				e)	是否发生费用为（1：是），
//		f)	费用收支类型为（1：收入）
//		g)	费用金额为租车费用。

		Map<String,Object> map7= new HashMap<>();
		map7.put("create_time",TimeUtil.dateToStr2(d));
		map7.put("record_id",br.getRecord_id());
		map7.put("deal_name","还入");
		map7.put("deal_type",3);
		map7.put("is_fee",1);
		map7.put("chg_money",feiyong);
		map7.put("fee_type",1);
		int yb = bicycleDealMapper.rentAndReturnUpdate(map7);
		System.out.println("yb "+yb);
		if((k+l+c+j+yb+y)!=6){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 99;
		}else {
			return 1;
		}
	}


	@Override
	public int YesOrNoToRent(String card_code) {
		Card card =  cardMapper.getUserCardMsg(card_code);
		if(card == null){
			return 9;
		}
		if(card.getZxbj().equals("1")) {
			return 0;  //已注销
		}
		 if(card.getStatus()== 2){
			return 2; // 锁定
		}
		 if(card.getStatus() == 3){
			 return 3; //挂失
		 }
		Integer card_id = card.getCard_id();
		if(bicycleInfoMapper.cardHaveBicycle(card_id)>=1){
			return 5; //已租车
		}
		 if(card.getFrozen_money() + card.getWallet_money() < 200){
		 	return 4;//冻结金额+余额 足部
		 }

		 return 1;
	}

	@Override
	public int YesOrNoToReturn(String card_code) {
		Card card =  cardMapper.getUserCardMsg(card_code);
		if(card == null){
			return 9;
		}
		if(card.getZxbj().equals("1")) {
			return 0;  //已注销
		}
		if(card.getStatus()== 2){
			return 2; // 锁定
		}
		if(card.getStatus() == 3){
			return 3; //挂失
		}
		Integer card_id = card.getCard_id();
		if(bicycleInfoMapper.cardHaveBicycle(card_id)==0){
			return 5; //为租车
		}
		return 1;
	}

	
}
