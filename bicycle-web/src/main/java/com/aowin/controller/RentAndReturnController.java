package com.aowin.controller;


import com.aowin.model.BicyclePile;
import com.aowin.model.BicycleStation;
import com.aowin.model.Card;
import com.aowin.model.Syuser;
import com.aowin.service.RentAndReturnService;
import com.aowin.service.StationService;
import com.aowin.util.TimeUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/rentAndReturn")
public class RentAndReturnController {

    final
    StationService stationService;

    final
    RentAndReturnService rentAndReturnService;

    public RentAndReturnController(StationService stationService,RentAndReturnService rentAndReturnService) {
        this.stationService = stationService;
        this.rentAndReturnService = rentAndReturnService;
    }


    @RequestMapping("/userGetStationMsg")
    @ResponseBody
    public PageInfo<Map<String,Object>> userGetStationMsg(Integer pageNum, String key){
        Map<String,Object> map = new HashMap<>();
        map.put("key",key);
        PageHelper.startPage(pageNum,8);
        Page<Map<String,Object>> page = (Page<Map<String, Object>>) stationService.userGetStationMsg(map);
        return page.toPageInfo();
    }

    @RequestMapping("/userGetStationPile")
    @ResponseBody
    public List<Map<String,Object>> userGetStationPile(Integer station_id){
        return stationService.userGetStationPile(station_id);
    }

    @RequestMapping("/determineStatus")
    @ResponseBody
    public String determineStatus(Integer pile_id){
        return stationService.getPileStatus(pile_id);
    }


    @RequestMapping("/rentBicycle")
    @ResponseBody
    public String rentBicycle(String card_code,Integer pile_id,Integer bicycle_id){
        int i = rentAndReturnService.YesOrNoToRent(card_code);
        if(i!=1){
            return i+"";
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("pile_id",pile_id);
            map.put("bicycle_id",bicycle_id);
            map.put("card_code",card_code);
            int j = rentAndReturnService.RentBicycle(map);
           return j+"";
        }

    }

    @RequestMapping("/returnBicycle")
    @ResponseBody
    public String returnBicycle(String card_code,Integer pile_id){
        int i = rentAndReturnService.YesOrNoToReturn(card_code);
        if(i!=1){
            return i+"";
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("pile_id",pile_id);
            map.put("card_code",card_code);
            int j = rentAndReturnService.ReturnBicycle(map);
            return j+"";
        }

    }

    public static void main(String[] args) {

    }
}
