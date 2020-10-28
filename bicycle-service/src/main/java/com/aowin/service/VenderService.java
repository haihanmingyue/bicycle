package com.aowin.service;

import com.aowin.model.Syuser;
import com.aowin.model.Vender;

import java.util.List;
import java.util.Map;

public interface VenderService {
	/**
	 *
	 * @param
	 * @return
	 */
	public List<Vender> getAllVender(Map<String,Object> map);

	public Vender getVenderById(Integer vender_id);

	public List<Vender> getVenderList();

	public int addVender(Vender vender) throws Exception;

	public int selectVenderByCode(Vender vender);

	public int updateVender(Vender vender) throws Exception;

	public int cancelVender(Map<String,Object> map) throws Exception;

}
