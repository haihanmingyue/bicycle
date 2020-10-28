package com.aowin.service;

import com.aowin.model.BicyclePile;
import com.aowin.model.BicycleStation;
import com.aowin.model.Vender;

import java.util.List;
import java.util.Map;

public interface StationService {

    public BicyclePile PileMsgById(Integer pile_id);

    public List<BicycleStation> getAllStation(Map<String,Object> map);

    public List<Map<String,Object>> userGetStationMsg(Map<String,Object> map);

    public List<BicyclePile> getStation_AllPile(Integer station_id);

    public List<Map<String,Object>> userGetStationPile(Integer station_id);

    public BicycleStation getStationById(Integer station_id);

    public int addPile(BicyclePile bicyclePile) throws Exception;

    public int getPileByCode(BicyclePile bicyclePile);

    public String getPileStatus(Integer pile_id);//获取车桩状态

    public int addStation(BicycleStation bicycleStation) throws Exception;

    public int selectStation(BicycleStation bicycleStation);

    public int getStation_PileCount(Integer station_id);

    public int updatePile(BicyclePile bicyclePile) throws Exception;

    public int updateStation(BicycleStation bicycleStation) throws Exception;
}
