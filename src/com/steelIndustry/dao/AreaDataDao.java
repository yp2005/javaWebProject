package com.steelIndustry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.steelIndustry.framework.dao.EntityJpaDao;
import com.steelIndustry.model.AreaData;

@Repository
public interface AreaDataDao extends EntityJpaDao<AreaData, Integer> {
	@Query("select a from AreaData a where a.areaDeep = 1")
	public List<AreaData> allAreaData();
}
