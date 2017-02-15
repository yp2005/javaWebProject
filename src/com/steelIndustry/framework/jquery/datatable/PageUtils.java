package com.steelIndustry.framework.jquery.datatable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

/**
 * @author wanglei
 *
 */
public class PageUtils {
	public static Map<String, Object> getMap(Page<?> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("draw", 1);
		map.put("recordsTotal", page.getTotalElements());
		map.put("recordsFiltered", page.getTotalElements());
		List<?> content = page.getContent();
		map.put("data", content);
		return map;
	}
	public static Map<String, Object> getMap2(Page<?> page) {
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("draw", 1);
		map.put("recordsTotal", page.getTotalElements());
		List<?> content = page.getContent();
		map.put("data", content);
		return map;
	}
}