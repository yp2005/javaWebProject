package com.steelIndustry.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.steelIndustry.model.AreaData;
import com.steelIndustry.model.TestParaOb;
import com.steelIndustry.service.AreaDataService;

@Controller
public class Test {
    @Resource(name = "areaDataService")
    private AreaDataService areaDataService;
    
	@RequestMapping("/helloworld")
	public String hello() {
		System.out.println("hello world");
		AreaData a = areaDataService.allAreaData().get(0);
		return "views/test.jsp";
	}
	
	@RequestMapping(value = "/allAreaData", method=RequestMethod.GET)
    @ResponseBody
    public List<AreaData> allAreaData() {
        List<AreaData> result = areaDataService.allAreaData();
        return result;
    }
	
	@RequestMapping(value = "/helloddd", method=RequestMethod.POST)
	public void helloddd(@RequestBody String data,PrintWriter pw) {
		System.out.println("hello helloddd" + data);
		pw.write("{\"a\":1}");
	}
	
	@RequestMapping(value = "/post", method=RequestMethod.POST)
	public void post(@RequestBody String data, HttpServletResponse response) throws IOException {
		System.out.println("================"+data);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		JSONObject dataJson;
		try {
			dataJson = JSONObject.parseObject(data);
		} catch (Exception e) {
			pw.write("{\"error\": \"json格式错误\"}");
			return;
		}
		System.out.println(dataJson.get("a"));
		pw.write("{\"result\": \"返回数据\"}");
	}
	
	@RequestMapping(value = "/object", method=RequestMethod.POST)
	public void object(@RequestBody TestParaOb data, HttpServletResponse response) throws IOException {
		System.out.println("==============="+data);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		System.out.println(data.getA());
		pw.write("{\"result\": \"返回数据\"}");
	}
	
	@RequestMapping(value = "/objectreturn", method=RequestMethod.POST)
	@ResponseBody
	public TestParaOb objectreturn(@RequestBody TestParaOb data) throws IOException {
		System.out.println("==============="+data);
		return data;
	}
}