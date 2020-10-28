package com.aowin.dao;

import com.aowin.model.Vender;

import java.util.List;
import java.util.Map;

public interface VenderMapper {

    public List<Vender> getAllVender(Map<String,Object> map);

    public Vender getVenderById(Integer vender_id);

    public List<Vender> getVenderList();//添加车桩时使用

    public int selectVenderByCode(Vender vender);

    public int addVender(Vender vender) throws Exception;

    public int updateVender(Vender vender) throws Exception;

    public int cancelVender(Map<String,Object> map) throws Exception;
}
