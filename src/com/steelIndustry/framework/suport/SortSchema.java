package com.steelIndustry.framework.suport;

import java.util.HashMap;
import java.util.Map;

public class SortSchema {
	private Map<String,Boolean> map = new HashMap<String, Boolean>();
	public SortSchema(String name,Boolean isAsc){
		this.map.put(name, isAsc);
	}
	public SortSchema addSort(String name,Boolean isAsc){
		this.map.put(name, isAsc);
		return this;
	}
	public Map<String,Boolean> getSchema(){
		return this.map;
	}
}
