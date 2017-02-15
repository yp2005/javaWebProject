package com.steelIndustry.framework.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.steelIndustry.framework.base.BaseSimplePojo;

public interface DataService<T extends BaseSimplePojo, ID extends Serializable> {
	List<T> findAll();

	List<T> findAll(Sort sort);

	List<T> findAll(Iterable<ID> ids);

	Page<T> findAll(Pageable pageable);

	T findOne(ID id);

	T findOne(ID id, boolean lazy);

	boolean exists(ID id);

	<S extends T> T save(S entity);

	<S extends T> List<S> save(Iterable<S> entities);

	void delete(ID id);

	void delete(T entity);

	void delete(Iterable<? extends T> entities);

	void deleteByIds(Iterable<ID> ids);
}
