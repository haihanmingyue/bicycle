package com.aowin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aowin.group.Update;
import com.aowin.model.MsPhase;
import com.aowin.service.impl.MsPhaseServiceImpl;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/main/msphase")
public class MsPhaseController {
	
	@Autowired
	private MsPhaseServiceImpl msPhaseServiceImpl;
	
	@RequestMapping("/select")
	public PageInfo<MsPhase> select(@RequestParam(defaultValue = "1") int pageNum,MsPhase msPhase){
		return msPhaseServiceImpl.select(pageNum, msPhase);
	}
	
	/**
	 * 数据校验 在需要被校验的对象上 添加@Validated 跟在这个参数后面添加一个BindingResult的对象
	 * 
	 * BindingResult 对象用来保存验证的结果的
	 * @param msPhase
	 * @param br
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(@Validated /* (value = {Update.class}) */ MsPhase msPhase,BindingResult br) {
		if(br.hasErrors()) {
			return "fail";
		}
		msPhaseServiceImpl.insert(msPhase);
		return "success";
		
	}

}
