package com.steelIndustry.framework.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.orm.jpa.vendor.Database;

import com.steelIndustry.framework.base.BaseSimplePojo;

@NoRepositoryBean
public interface EntityJpaDao<Entity extends BaseSimplePojo, ID extends Serializable>
		extends JpaRepository<Entity, ID> {

	public EntityManager getEntityManager();

	public Database getDatabaseType();

	public void deleteByIds(Iterable<ID> ids);

	public List<Object[]> executeNativeSQL(String sql, Map<String, Object> params);

	public List<Object[]> executeNativeSQL(int first, int max, String sql, Map<String, Object> params);

	public List<Entity> executeNativeSQL(String sql, Map<String, Object> params, Class<Entity> clazz);

	public List<Map<String, Object>> findAllMapBySQL(String sql, Map<String, Object> params);

	public Page<Entity> executeJPQL(CharSequence jpql, CharSequence countProjection, Pageable pageable,
			Map<String, Object> params);

	public List<Entity> executeJPQL(CharSequence jpql, Map<String, Object> params);
	
}
