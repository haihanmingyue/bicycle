package com.aowin.dao;

import com.aowin.model.BicycleRecord;

import java.util.Map;

public interface BicycleRecordMapper {

 public int rentAndReturnInsert(Map<String,Object> map);

 public int update(Map<String,Object> map);

 public BicycleRecord selectByCardId(Integer card_id);

 public int selectById(Integer record_id);
}
