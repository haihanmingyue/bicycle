package com.aowin.service.impl;

import com.aowin.dao.SyuserMapper;
import com.aowin.dao.VenderMapper;
import com.aowin.model.Syuser;
import com.aowin.model.Vender;
import com.aowin.service.SyuserService;
import com.aowin.service.VenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class VenderServiceImpl implements VenderService{

	final
	VenderMapper venderMapper;

	public VenderServiceImpl(VenderMapper venderMapper) {
		this.venderMapper = venderMapper;
	}


	@Override
	public List<Vender> getAllVender(Map<String, Object> map) {
		return venderMapper.getAllVender(map);
	}

	@Override
	public Vender getVenderById(Integer vender_id) {
		return venderMapper.getVenderById(vender_id);
	}

	@Override
	public List<Vender> getVenderList() {
		return venderMapper.getVenderList();
	}

	@Override
	@Transactional
	public int addVender(Vender vender){

		Random random = new Random();
		while (true){
			StringBuilder venderCode = new StringBuilder();
			String code;
			for(int k=0;k<6;k++){
				int j = random.nextInt(10);
				venderCode.append(j);
			}
			code = venderCode.toString();
			vender.setVender_code(code);
			int count = venderMapper.selectVenderByCode(vender);
			if(count==0){
				break;
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date d = new Date();
		String s = sdf.format(d);
		vender.setCreate_time(s);
		vender.setZxbj("0");
		int i = 0;
		try {
			i = venderMapper.addVender(vender);
			if(i==0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return i;
	}

	@Override
	public int selectVenderByCode(Vender vender) {
		return venderMapper.selectVenderByCode(vender);
	}

	@Override
	@Transactional
	public int updateVender(Vender vender) {
		int i = 0;
		try {
			i = venderMapper.updateVender(vender);
			if(i ==0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return i;
	}

	@Override
	@Transactional
	public int cancelVender(Map<String,Object> map) throws Exception {
		int i = 0;
		try {
			i = venderMapper.cancelVender(map);
			if(i ==0){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return i;
	}


}
