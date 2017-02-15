package com.steelIndustry.framework.suport;



public class DynamicSpecification{
	private Operators operator;
	private String name;
	private Object value;
	public DynamicSpecification(Operators operator,String name,Object... value){
		this.operator = operator;
		this.name = name;
		this.value = value;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
