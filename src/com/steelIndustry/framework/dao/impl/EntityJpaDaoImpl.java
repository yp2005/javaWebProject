package com.steelIndustry.framework.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.util.CollectionUtils;

import com.steelIndustry.framework.base.BaseSimplePojo;
import com.steelIndustry.framework.base.CodeGenerator;
import com.steelIndustry.framework.dao.EntityJpaDao;
import com.steelIndustry.util.GeneratorUtil;

@NoRepositoryBean
@SuppressWarnings({ "all" })
public class EntityJpaDaoImpl<Entity extends BaseSimplePojo, ID extends Serializable>
		extends SimpleJpaRepository<Entity, ID> implements EntityJpaDao<Entity, ID> {
	private static final Logger LOG = Logger.getLogger(EntityJpaDaoImpl.class.getName());
	private JpaEntityInformation<Entity, ?> jpaEntityInformation;
	private EntityManager entityManager;
	private Database databaseType;

	public EntityJpaDaoImpl(JpaEntityInformation<Entity, ?> entityInformation, EntityManager entityManager) {

		super(entityInformation, entityManager);
		LOG.config("Initialization EntityJpaDaoImpl " + entityInformation.getEntityName());
		this.jpaEntityInformation = entityInformation;
		this.entityManager = entityManager;
		Session session = (Session) this.entityManager.getDelegate();
		String databaseName = ((SessionImpl) session).getFactory().getDialect().getClass().getName().toUpperCase();
		if (databaseName.indexOf("ORACLE") != -1) {
			databaseType = Database.ORACLE;
		} else if (databaseName.indexOf("POSTGRESQL") != -1) {
			databaseType = Database.POSTGRESQL;
		} else if (databaseName.indexOf("MYSQL") != -1) {
			databaseType = Database.MYSQL;
		} else if (databaseName.indexOf("DB2") != -1) {
			databaseType = Database.DB2;
		} else if (databaseName.indexOf("SQLSERVER") != -1) {
			databaseType = Database.SQL_SERVER;
		} else if (databaseName.indexOf("SYBASE") != -1) {
			databaseType = Database.SYBASE;
		}
		LOG.config("Database " + databaseType.name());
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Database getDatabaseType() {
		return this.databaseType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeNativeSQL(String sql, Map<String, Object> params) {

		Query query = getEntityManager().createNativeQuery(sql);
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> executeNativeSQL(int first, int max, String sql, Map<String, Object> params) {
		Query query = getEntityManager().createNativeQuery(sql).setFirstResult(first).setMaxResults(max);
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entity> executeNativeSQL(String sql, Map<String, Object> params, Class<Entity> clazz) {
		Query query = getEntityManager().createNativeQuery(sql, clazz);
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return (List<Entity>) query.getResultList();
	}

	@Override
	public List<Map<String, Object>> findAllMapBySQL(String sql, Map<String, Object> params) {
		Query query = getEntityManager().createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

	public JpaEntityInformation<Entity, ?> getJpaEntityInformation() {
		return jpaEntityInformation;
	}

	@Override
	public <S extends Entity> S save(S entity) {
		List<Entity> entities = new ArrayList<>();
		entities.add(entity);
		generatorCode(entities);
		return super.save(entity);
	}

	@Override
	public <S extends Entity> List<S> save(Iterable<S> entities) {
		generatorCode(entities);
		return super.save(entities);
	}

	private <S extends Entity> void generatorCode(Iterable<S> entitys) {
		for (Entity entity : entitys) {
			if (entity instanceof CodeGenerator) {
				CodeGenerator codeGenerator = (CodeGenerator) entity;
				String code = GeneratorUtil.generatorCode(codeGenerator.getBusinessCodeAlias());
				codeGenerator.setCode(code);
			}
		}
	}

	@Override
	public Page<Entity> executeJPQL(CharSequence jpql, CharSequence countProjection, Pageable pageable,
			Map<String, Object> params) {
		String qlString = jpql.toString();
		String alias = QueryUtils.detectAlias(qlString);
		String sortedQL = QueryUtils.applySorting(qlString, pageable.getSort(), alias);
		TypedQuery<Long> countQuery = getCountQuery(sortedQL,
				countProjection == null ? null : countProjection.toString(), params);
		return readPage(getQuery(sortedQL, params), pageable, countQuery);
	}

	@Override
	public List<Entity> executeJPQL(CharSequence jpql, Map<String, Object> params) {
		return getQuery(jpql.toString(), params).getResultList();
	}

	protected Page<Entity> readPage(TypedQuery<Entity> query, Pageable pageable, TypedQuery<Long> countQuery) {
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		Long total = countQuery.getSingleResult();
		List<Entity> content = total > pageable.getOffset() ? query.getResultList() : Collections.<Entity> emptyList();
		if (CollectionUtils.isEmpty(content)) {
			return new PageImpl(Collections.emptyList());
		}
		return new PageImpl<Entity>(content, pageable, total);
	}

	protected TypedQuery<Long> getCountQuery(String jpql, String countProjection, Map<String, Object> params) {
		return applyQueryParameters(createCountQuery(jpql, countProjection), params);
	}

	private TypedQuery<Long> createCountQuery(String jpql, String projection) {
		jpql = StringUtils.normalizeSpace(jpql.replaceAll("(?i)fetch", ""));
		return entityManager.createQuery(QueryUtils.createCountQueryFor(jpql, projection), Long.class);
	}

	protected TypedQuery<Entity> getQuery(String jpql, Map<String, Object> params) {
		jpql = StringUtils.normalizeSpace(jpql);
		return applyQueryParameters(entityManager.createQuery(jpql, jpaEntityInformation.getJavaType()), params);
	}

	private <E> TypedQuery<E> applyQueryParameters(TypedQuery<E> query, Map<String, Object> params) {
		Set<Parameter<?>> parameters = query.getParameters();
		for (Parameter<?> parameter : parameters) {
			String name = parameter.getName();
			query.setParameter(name, params.get(name));
		}
		return query;
	}

	@Override
	public void deleteByIds(Iterable<ID> ids) {
		List<Entity> entities = findAll(ids);
		if (!CollectionUtils.isEmpty(entities)) {
			for (Entity entity : entities) {
				delete(entity);
			}
		}
	}
}
