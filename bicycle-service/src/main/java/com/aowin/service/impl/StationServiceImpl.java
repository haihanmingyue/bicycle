package com.aowin.service.impl;

import com.aowin.dao.BicyclePileMapper;
import com.aowin.dao.BicycleStationMapper;
import com.aowin.dao.VenderMapper;
import com.aowin.model.BicyclePile;
import com.aowin.model.BicycleStation;
import com.aowin.model.Vender;
import com.aowin.service.StationService;
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
public class StationServiceImpl implements StationService {

	final
	BicycleStationMapper bicycleStationMapper;
	final
	BicyclePileMapper bicyclePileMapper;
	public StationServiceImpl(BicycleStationMapper bicycleStationMapper, BicyclePileMapper bicyclePileMapper) {
		this.bicycleStationMapper = bicycleStationMapper;
		this.bicyclePileMapper = bicyclePileMapper;
	}


	@Override
	public BicyclePile PileMsgById(Integer pile_id) {
		return bicyclePileMapper.PileMsgById(pile_id);
	}

	@Override
	public List<BicycleStation> getAllStation(Map<String, Object> map) {
		return bicycleStationMapper.getAllStation(map);
	}

	@Override
	public List<Map<String, Object>> userGetStationMsg(Map<String,Object> map) { //租车用户获取车点
		return bicycleStationMapper.userGetStationMsg(map);
	}

	@Override
	public List<BicyclePile> getStation_AllPile(Integer station_id) {
		return bicyclePileMapper.getStation_AllPile(station_id);
	}

	@Override
	public List<Map<String,Object>> userGetStationPile(Integer station_id) {
		return bicyclePileMapper.userGetStationPile(station_id);
	}

	@Override
	public BicycleStation getStationById(Integer station_id) {
		return bicycleStationMapper.getStationById(station_id);
	}

	@Override
	@Transactional
	public int addPile(BicyclePile bicyclePile) throws Exception {
		int i = 0;
		try {
			i = bicyclePileMapper.addPile(bicyclePile);
		}catch (Exception e){
			e.printStackTrace();
			throw new Exception();
		}
		return i;
	}

	@Override
	public int getPileByCode(BicyclePile bicyclePile) {
		return bicyclePileMapper.getPileByCode(bicyclePile);
	}

	@Override
	public String getPileStatus(Integer pile_id) {
		return bicyclePileMapper.getPileStatus(pile_id);
	}

	@Override
	@Transactional
	public int addStation(BicycleStation bicycleStation) throws Exception {
		int i = 0;
			try {
				i = bicycleStationMapper.addStation(bicycleStation);
			}catch (Exception e){
				e.printStackTrace();
				throw new Exception();
			}
			return i;
		}

	@Override
	public int selectStation(BicycleStation bicycleStation) {
		return bicycleStationMapper.selectStation(bicycleStation);
	}

	@Override
	public int getStation_PileCount(Integer station_id) {
		return bicyclePileMapper.getStation_PileCount(station_id);
	}

	@Override
	public int updatePile(BicyclePile bicyclePile) throws Exception {
		int i = 0;
		try {
			i = bicyclePileMapper.updatePile(bicyclePile);
		}catch (Exception e){
			e.printStackTrace();
			throw new Exception();
		}
		return i;
	}

	@Override
	@Transactional
	public int updateStation(BicycleStation bicycleStation) throws Exception {
		int i = 0;
		try {
			i = bicycleStationMapper.updateStation(bicycleStation);
		}catch (Exception e){
			e.printStackTrace();
			throw new Exception();
		}
		return i;
	}


}
