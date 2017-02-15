package com.steelIndustry.framework.base;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

@SuppressWarnings({ "serial", "unchecked" })
public abstract class BasePojo implements Pojo {
	@Override
	public <T> T get(String name) {
		try {
			return (T) PropertyUtils.getProperty(this, name);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> void set(String name, T value) {
		try {
			PropertyUtils.setProperty(this, name, value);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean readable(String name) {
		return PropertyUtils.isReadable(this, name);
	}

	@Override
	public boolean writable(String name) {
		return PropertyUtils.isWriteable(this, name);
	}

	@Override
	public <T> Class<T> getType(String name) throws ReflectiveOperationException {
		return (Class<T>) PropertyUtils.getPropertyType(this, name);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			return BeanUtils.cloneBean(this);
		} catch (ReflectiveOperationException e) {
			throw new CloneNotSupportedException(e.getMessage());
		}
	}

}
