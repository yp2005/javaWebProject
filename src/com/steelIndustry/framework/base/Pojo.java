package com.steelIndustry.framework.base;

public interface Pojo extends Getter, Setter,BaseSimplePojo {
	boolean readable(String name);
	boolean writable(String name);

	<T> Class<T> getType(String name) throws ReflectiveOperationException;
}
