package com.steelIndustry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.steelIndustry.dao.AreaDataDao;
import com.steelIndustry.framework.dao.EntityJpaDao;
import com.steelIndustry.framework.service.impl.DataServiceImpl;
import com.steelIndustry.model.AreaData;
import com.steelIndustry.service.AreaDataService;

@Service("areaDataService")
public class AreaDataServiceImpl extends DataServiceImpl<AreaData, Integer> implements AreaDataService {

	@Resource
	private AreaDataDao areaDataDao;
	
	@Override
	public List<AreaData> allAreaData() {
	    return areaDataDao.allAreaData();
	}

    @Override
    public EntityJpaDao<AreaData, Integer> getRepository() {
        return  areaDataDao;
    }

}
