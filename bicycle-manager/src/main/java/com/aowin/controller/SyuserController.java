package com.aowin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aowin.model.Syuser;
import com.aowin.service.SyuserService;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SyuserController {

	private final SyuserService syuserServiceImpl;

	public SyuserController(SyuserService syuserServiceImpl) {
		this.syuserServiceImpl = syuserServiceImpl;
	}


	@RequestMapping("/login")
	public Syuser login(@Validated Syuser syuser,BindingResult br, HttpSession session) {
		//如果验证不合法 直接返回
		if(br.hasErrors()) {
			return null;
		}
		Syuser user = syuserServiceImpl.login(syuser);
		if(user != null) {
			session.setAttribute("syuser", user);
		}
		return user;
	}

	@RequestMapping("/lgout")
	public ModelAndView lgOut(ModelAndView model,HttpSession session) {
		//如果验证不合法 直接返回
		session.invalidate();
		model.setViewName("redirect:index.html");
		return model;
	}
}
