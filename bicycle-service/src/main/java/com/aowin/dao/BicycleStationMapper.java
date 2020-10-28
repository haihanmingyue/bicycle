package com.aowin.dao;

import com.aowin.model.BicycleStation;
import com.aowin.model.Vender;

import java.util.List;
import java.util.Map;

public interface BicycleStationMapper {

    public List<BicycleStation> getAllStation(Map<String,Object> map);

    public List<Map<String,Object>> userGetStationMsg(Map<String,Object> map);

    public BicycleStation getStationById(Integer station_id);

    public int selectStation(BicycleStation bicycleStation);

    public int addStation(BicycleStation bicycleStation) throws Exception;

    public int updateStation(BicycleStation bicycleStation) throws Exception;

    public int cancelStation(Map<String,Object> map) throws Exception;
}
