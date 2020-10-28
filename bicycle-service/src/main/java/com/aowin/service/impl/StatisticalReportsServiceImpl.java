package com.aowin.service.impl;

import com.aowin.dao.BicycleInfoMapper;
import com.aowin.service.StatisticalReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 统计报表服务
 * */

@Service
public class StatisticalReportsServiceImpl implements StatisticalReportsService {

    @Autowired
    BicycleInfoMapper bicycleInfoMapper;

    @Override
    public List<Map<String, Object>> yearOrMonthBicycleFee(Map<String, Object> map) {
        return bicycleInfoMapper.yearOrMonthBicycleFee(map);
    }

    @Override
    public Map<String, Object> yearOrMonthAllFee(Map<String, Object> map) {
        return bicycleInfoMapper.yearOrMonthAllFee(map);
    }
}
