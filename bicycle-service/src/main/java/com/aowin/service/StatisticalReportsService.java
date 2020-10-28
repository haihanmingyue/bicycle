package com.aowin.service;

import java.util.List;
import java.util.Map;

/**
 * 统计报表服务
 * */
public interface StatisticalReportsService {

    public List<Map<String,Object>> yearOrMonthBicycleFee(Map<String,Object>map);

    public Map<String,Object> yearOrMonthAllFee(Map<String,Object>map);
}
