package com.steelIndustry.framework.suport;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator {
	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		String columnName = session.getFactory().getIdentifierPropertyName(object.getClass().getName());
		String getMethodName = "get"+columnName.substring(0, 1).toUpperCase()+columnName.substring(1);
		
		try {
			Method getMethod = object.getClass().getMethod(getMethodName);
			Object value = getMethod.invoke(object);
			if(value==null){
				throw new HibernateException("Identifier is null");
			}
			if(!(value instanceof Serializable)){
				throw new HibernateException("Identifier is not implement Serializable");
			}
			System.out.println(value);
			return (Serializable)value;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
