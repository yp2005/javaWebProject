package com.steelIndustry.framework.web;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import com.steelIndustry.framework.base.BaseSimplePojo;
import com.steelIndustry.framework.dao.EntityJpaDao;
import com.steelIndustry.framework.dao.impl.EntityJpaDaoImpl;


public class EntityRepositoryFactory<Entity extends BaseSimplePojo,ID extends Serializable> extends JpaRepositoryFactory {
	private EntityManager entityManager;
	public EntityRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Object getTargetRepository(RepositoryInformation information) {
		return new EntityJpaDaoImpl<Entity,ID>(getEntityInformation((Class<Entity>)information.getDomainType()), entityManager);
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return EntityJpaDao.class;
	}
	
}
