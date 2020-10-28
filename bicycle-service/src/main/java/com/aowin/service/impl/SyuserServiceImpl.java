package com.aowin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aowin.dao.SyuserMapper;
import com.aowin.model.Syuser;
import com.aowin.service.SyuserService;

@Service
public class SyuserServiceImpl implements SyuserService{

	final
	SyuserMapper syuserMapper;

	@Autowired
	public SyuserServiceImpl(SyuserMapper syuserMapper) {
		this.syuserMapper = syuserMapper;
	}


	@Override
	public Syuser login(Syuser syuser) {
		return syuserMapper.login(syuser);
	}

	public static void main(String[] args) {


	}
}
