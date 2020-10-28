package com.aowin.dao;

import com.aowin.model.BicyclePile;
import com.aowin.model.Vender;

import java.util.List;
import java.util.Map;

public interface BicyclePileMapper {

    public List<BicyclePile> getStation_AllPile(Integer station_id);

    public BicyclePile PileMsgById(Integer pile_id);

    public List<Map<String,Object>> userGetStationPile(Integer station_id);

    public int rentAndReturnUpdate(Map<String,Object> map);

    public String getPileStatus(Integer pile_id);

    public int getStation_PileCount(Integer station_id);

    public int getPileByCode(BicyclePile bicyclePile);

    public int selectPileById(BicyclePile bicyclePile);

    public int addPile(BicyclePile bicyclePile) throws Exception;

    public int updatePile(BicyclePile bicyclePile) throws Exception;

    public int cancelPile(Map<String,Object> map) throws Exception;
}
