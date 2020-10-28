package com.aowin.controller;


import com.alibaba.fastjson.JSON;
import com.aowin.model.BicycleStation;
import com.aowin.service.StatisticalReportsService;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import netscape.javascript.JSObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/main/report")
public class ReportController {

	final StatisticalReportsService statisticalReportsService;

	public ReportController(StatisticalReportsService statisticalReportsService) {
		this.statisticalReportsService = statisticalReportsService;
	}

	@RequestMapping(value = "/getReportAll") //这个车辆费用总的报表
	@ResponseBody
	public Map<String,Object> getReportAll(@RequestParam Map<String,String> resultMap){
		Map<String,Object> map = new HashMap<>();
		map.put("bicycle_code",resultMap.get("resultMap[bicycle_code]").trim());
		map.put("time",resultMap.get("resultMap[time]").trim());
		System.out.println(statisticalReportsService.yearOrMonthAllFee(map));
		return statisticalReportsService.yearOrMonthAllFee(map);
	}

	@RequestMapping(value = "/getReport") //这个是车辆费用精细到每辆车的报表
	@ResponseBody
	public PageInfo<Map<String,Object>> getReport(@RequestParam Map<String,String> resultMap){
		Map<String,Object> map = new HashMap<>();
		map.put("bicycle_code",resultMap.get("resultMap[bicycle_code]").trim());
		map.put("time",resultMap.get("resultMap[time]").trim());

		String pageNum = (String) resultMap.get("resultMap[pageNum]");
		PageHelper.startPage(Integer.parseInt(pageNum.trim()),8);
		Page<Map<String,Object>> page = (Page<Map<String, Object>>) statisticalReportsService.yearOrMonthBicycleFee(map);

		return page.toPageInfo();
	}




}
