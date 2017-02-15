package com.steelIndustry.framework.web;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class EntityRepositoryFactoryBean<Dao extends JpaRepository<Entity, ID>,Entity,ID extends Serializable> extends JpaRepositoryFactoryBean<Dao, Entity, ID> {
	@SuppressWarnings("rawtypes")
	@Override
	protected RepositoryFactorySupport createRepositoryFactory(
			EntityManager entityManager) {
		return new EntityRepositoryFactory(entityManager);
	}

}
