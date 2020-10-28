package com.aowin.dao;

import com.aowin.model.Syuser;

public interface SyuserMapper {
	/**
	 * 登录
	 * @param syuser
	 * @return
	 */
	public Syuser login(Syuser syuser);

}
