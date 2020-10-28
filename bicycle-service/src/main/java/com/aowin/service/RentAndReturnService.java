package com.aowin.service;

import com.aowin.model.BicyclePile;
import com.aowin.model.BicycleStation;
import com.aowin.model.Card;

import java.util.List;
import java.util.Map;

public interface RentAndReturnService {

    public int RentBicycle(Map<String,Object> map);

    public int ReturnBicycle(Map<String,Object> map);
    public int YesOrNoToRent(String card_code); //是否能进行租车

    public int YesOrNoToReturn(String card_code); //是否能进行还车

}
