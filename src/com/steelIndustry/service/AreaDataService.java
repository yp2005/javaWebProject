package com.steelIndustry.service;

import java.util.List;

import com.steelIndustry.framework.service.DataService;
import com.steelIndustry.model.AreaData;


public interface AreaDataService extends DataService<AreaData, Integer> {
    public List<AreaData> allAreaData();
}
