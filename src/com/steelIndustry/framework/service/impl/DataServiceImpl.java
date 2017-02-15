package com.steelIndustry.framework.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.steelIndustry.framework.base.BaseSimplePojo;
import com.steelIndustry.framework.dao.EntityJpaDao;
import com.steelIndustry.framework.dao.RepositoryAccessor;
import com.steelIndustry.framework.service.DataService;

public abstract class DataServiceImpl<T extends BaseSimplePojo, ID extends Serializable>
		implements DataService<T, ID>, RepositoryAccessor<EntityJpaDao<T, ID>> {
	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return getRepository().findAll(sort);
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		return getRepository().findAll(ids);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	@Override
	public T findOne(ID id) {
		return findOne(id, false);
	}

	@Override
	public T findOne(ID id, boolean lazy) {
		return lazy ? getRepository().getOne(id) : getRepository().findOne(id);
	}

	@Override
	public <S extends T> T save(S entity) {
		return getRepository().save(entity);
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		List<S> pojos = getRepository().save(entities);
		getRepository().flush();
		return pojos;
	}

	@Override
	public boolean exists(ID id) {
		return getRepository().exists(id);
	}

	@Override
	public void delete(ID id) {
		getRepository().delete(id);
	}

	@Override
	public void delete(T entity) {
		getRepository().delete(entity);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		getRepository().delete(entities);
		getRepository().flush();
	}

	@Override
	public void deleteByIds(Iterable<ID> ids) {
		getRepository().deleteByIds(ids);
	}
}
