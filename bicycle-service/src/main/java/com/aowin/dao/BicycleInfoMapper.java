package com.aowin.dao;

import java.util.List;
import java.util.Map;

public interface BicycleInfoMapper {
    public int cardHaveBicycle(Integer card_id); //判断卡号是否有正在租用的车
    public int updateBicycleInfo(Map<String,Object> map);
    public int updateReturnBicycleInfo(Map<String,Object> map);

    public Map<String,Object> yearOrMonthAllFee(Map<String,Object>map);
    public List<Map<String,Object>> yearOrMonthBicycleFee(Map<String,Object>map);

}
