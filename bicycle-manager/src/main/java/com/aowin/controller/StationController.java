package com.aowin.controller;


import com.aowin.model.BicyclePile;
import com.aowin.model.BicycleStation;
import com.aowin.model.Syuser;
import com.aowin.model.Vender;
import com.aowin.service.StationService;
import com.aowin.service.VenderService;
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
import sun.misc.Cleaner;

import javax.servlet.http.HttpSession;
import java.util.*;


@RestController
@RequestMapping("/main/station")
public class StationController {

    final
    StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }


    @RequestMapping("/getStation")
    @ResponseBody
    public PageInfo<BicycleStation> getStation(Integer pageNum, String keysS){
        Map<String,Object> map = new HashMap<>();
        map.put("keysS",keysS);
        PageHelper.startPage(pageNum,8);
        Page<BicycleStation> page = (Page<BicycleStation>) stationService.getAllStation(map);
        return page.toPageInfo();
    }


    @RequestMapping("/getStation_pile")
    @ResponseBody
    public List<BicyclePile> getStation_pile(Integer station_id){
        System.out.println(station_id);
        return stationService.getStation_AllPile(station_id);
    }

    @RequestMapping("/getStationMsg")
    @ResponseBody
    public BicycleStation getStationMsg(Integer station_id){
        return stationService.getStationById(station_id);
    }


    @RequestMapping("/getStation_PileCount")
    @ResponseBody
    public String getStation_PileCount(Integer station_id){
        return stationService.getStation_PileCount(station_id)+"";
    }
    @RequestMapping("/getPile")
    @ResponseBody
    public BicyclePile getPile(Integer pile_id){
        System.out.println("pile_id: "+pile_id);
        return stationService.PileMsgById(pile_id);
    }


    @RequestMapping("/addStation")
    @ResponseBody
    public String addStation(@Validated BicycleStation bicycleStation,BindingResult br,HttpSession session){
        if(br.hasErrors()){
            return null;
        }

        BicycleStation b = new BicycleStation();
        b.setAddress(bicycleStation.getAddress());
        if(stationService.selectStation(b)>=1){
            return "3";
        }
        b.setAddress("");
        b.setStation_code(bicycleStation.getStation_code());
        if(stationService.selectStation(b)>=1){
            return "4";
        }
        b.setStation_code("");
        b.setStation_name(bicycleStation.getStation_name());
        if(stationService.selectStation(b)>=1){
            return "5";
        }
        b.setStation_name("");
        b.setLongitude((double)Math.round(bicycleStation.getLongitude()*100000)/100000);
        System.out.println(b.getLongitude());
        b.setLatitude((double)Math.round(bicycleStation.getLatitude()*100000)/100000);
        System.out.println(b.getLatitude());
        System.out.println(stationService.selectStation(b));
        if(stationService.selectStation(b)>=1){
            return "6";
        }
        Syuser syuser = (Syuser) session.getAttribute("syuser");
        Integer userid = syuser.getUserId();
        bicycleStation.setUser_id(userid);
        bicycleStation.setCreate_time(TimeUtil.dateToStr(new Date()));
        int i = 0;
        try {
            i  = stationService.addStation(bicycleStation);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i+"";
    }


    @RequestMapping("/updateStation")
    @ResponseBody
    public String updateStation(@Validated BicycleStation bicycleStation,BindingResult br,HttpSession session){
        if(br.hasErrors()){
            return null;
        }
        BicycleStation b1 = stationService.getStationById(bicycleStation.getStation_id());
        BicycleStation b = new BicycleStation();
        b.setAddress(bicycleStation.getAddress());
        if(stationService.selectStation(b)>=1 && !bicycleStation.getAddress().equals(b1.getAddress())){
            return "3";
        }
        b.setAddress("");
        b.setStation_code(bicycleStation.getStation_code());
        if(stationService.selectStation(b)>=1 && !bicycleStation.getStation_code().equals(b1.getStation_code())){
            return "4";
        }
        b.setStation_code("");
        b.setStation_name(bicycleStation.getStation_name());
        if(stationService.selectStation(b)>=1 && !bicycleStation.getStation_name().equals(b1.getStation_name()) ){
            return "5";
        }
        b.setStation_name("");
        b.setLongitude((double)Math.round(bicycleStation.getLongitude()*100000)/100000);
        b.setLatitude((double)Math.round(bicycleStation.getLatitude()*100000)/100000);
        if(stationService.selectStation(b)>=1 && !bicycleStation.getLongitude().equals(b1.getLongitude()) && !bicycleStation.getLatitude().equals(b1.getLatitude())){
            return "6";
        }
        Syuser syuser = (Syuser) session.getAttribute("syuser");
        Integer userid = syuser.getUserId();
        bicycleStation.setUser_id(userid);
        int i = 0;
        try {
            i  = stationService.updateStation(bicycleStation);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i+"";
    }



    @RequestMapping("/addPile")
    @ResponseBody
    public String addPile(BicyclePile bicyclePile,BindingResult br,HttpSession session){
        int k = stationService.getStation_PileCount(bicyclePile.getStation_id());//现在有多少个车桩
        int j = stationService.getStationById(bicyclePile.getStation_id()).getBicycle_pile_num();//设定最大车桩数
        if(k>=j){
            return "4";

        }
        BicyclePile bicyclePile1 = new BicyclePile();
        bicyclePile1.setPile_code(bicyclePile.getPile_code());
        if(stationService.getPileByCode(bicyclePile1) >=1){
            return "3";
        }

        Syuser syuser = (Syuser) session.getAttribute("syuser");
        Integer userid = syuser.getUserId();
        bicyclePile.setUser_id(userid);
        if(bicyclePile.getBicycle_id().equals("/")){
            bicyclePile.setBicycle_id("");
        }
        bicyclePile.setOperator_time(TimeUtil.dateToStr(new Date()));
        int i = 0;
        try {
            i  = stationService.addPile(bicyclePile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i+"";
    }


    @RequestMapping("/updatePile")
    @ResponseBody
    public String updatePile(BicyclePile bicyclePile,BindingResult br,HttpSession session){

        BicyclePile bicyclePile2 = stationService.PileMsgById(bicyclePile.getPile_id());

        BicyclePile bicyclePile1 = new BicyclePile();
        bicyclePile1.setPile_code(bicyclePile.getPile_code());
        if(stationService.getPileByCode(bicyclePile1) >=1 && !bicyclePile.getPile_code().equals(bicyclePile2.getPile_code())){//判断是不是原来的code
            return "3";
        }
        if(bicyclePile.getBicycle_id().equals("/")){
            bicyclePile.setBicycle_id("");
        }
        Syuser syuser = (Syuser) session.getAttribute("syuser");
        Integer userid = syuser.getUserId();
        bicyclePile.setUser_id(userid);
        bicyclePile.setOperator_time(TimeUtil.dateToStr(new Date()));
        int i = 0;
        try {
            i  = stationService.updatePile(bicyclePile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i+"";
    }


    public static void main(String[] args) {
      double x = 120.150366;
      System.out.println(x*100000);
      System.out.println(Math.round(x*100000));
        System.out.println((double)Math.round(x*100000)/100000);
    }
}
