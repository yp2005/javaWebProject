package com.steelIndustry.framework.dao;

@SuppressWarnings("rawtypes")
public interface RepositoryAccessor<T extends EntityJpaDao> {
	T getRepository();
}
