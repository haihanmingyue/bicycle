package com.aowin.dao;

import java.util.List;

import com.aowin.model.MsPhase;

public interface MsPhaseMapper {
	/**
	 * 新增权限
	 * @param msPhase
	 * @return
	 */
	public int insert(MsPhase msPhase);
	
	/**
	 * 修改权限
	 * @param msPhase
	 * @return
	 */
	public int update(MsPhase msPhase);
	/**
	 * 查询权限
	 * @param msPhase
	 * @return
	 */
	public List<MsPhase> select(MsPhase msPhase);
	
	/**
	 * 根据id查询权限
	 * @param phaseId
	 * @return
	 */
	public MsPhase selectById(int phaseId);
	/**
	 * 删除权限
	 * @param phaseId
	 * @return
	 */
	public int delete(int phaseId);
	

}
