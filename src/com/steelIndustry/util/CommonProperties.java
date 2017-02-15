package com.steelIndustry.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 读取配置文件工具类
 * @author lxm
 */

public class CommonProperties extends  Properties {
	private static final Log logger = LogFactory.getLog(CommonProperties.class);
	
	protected static String FILE_NAME = "common.properties";	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6748079991539205360L;

	public static CommonProperties getInstance(){
		return CommonPropertiesHandler.INSTANCE;
	}
	
	private static class CommonPropertiesHandler{
		private static final CommonProperties INSTANCE = new CommonProperties(FILE_NAME);
	}
	
	public CommonProperties(String fileName){
		try {
			init(fileName);
		} catch (FileNotFoundException e) {
			logger.error("find is not file");
		} catch (IOException e) {
			logger.error("find is not file");
		}
	}

	 /** 
     *写入properties信息 
     * @param parameterName 配置文件属性名 
     * @param parameterValue 需要写入的配置文件的信息 
     */  
    public void writeProperties(String parameterName,  
                                       String parameterValue){  
        try {
            //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
            //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。  
            OutputStream fos = new FileOutputStream(getConfigFile(FILE_NAME));  
            this.put(parameterName, parameterValue);  
            //以适合使用 load 方法加载到 Properties 表中的格式，  
            //将此 Properties 表中的属性列表（键和元素对）写入输出流  
            this.store(fos, " Update '" + parameterName + "' value");  
     //       setLastUpdateBalanceStat(parameterValue);  
        }  
        catch (IOException e) {  
        	logger.error("write is error");
        }  
    }  
    
	/**
     * 功能：获取properties对象
     * 描述：获取properties对象
     * @return 返回获取properties对象
     */
    private File getConfigFile(String fileName){
    	File file = new File("WebRoot" + File.separator + "props" + File.separator+fileName);
		if(!file.exists()){
			file = new File(".."+File.separator+"props" + File.separator+fileName);
		}
		if(!file.exists()){
			file = new File("props" + File.separator+fileName);
		}
		if(!file.exists()) {
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			file = new File(path + File.separator + "props" + File.separator+fileName);
		}
		return file;
    }
    
	protected void init(String fileName) throws FileNotFoundException, IOException{
		File file = getConfigFile(fileName);
		if(!file.exists()){
			this.load(CommonProperties.class.getClassLoader().getResourceAsStream("/"+fileName));			
		}else{
			this.load(new FileInputStream(file));
		}
	}

	public String getProperty(String key, String defaultValue){
		String result = this.getProperty(key);
		return CommonUtil.isEmpty(result) ? defaultValue : result; 
	}
	
	public int getPropertyForInt(String key){
		String temp = this.getProperty(key, "0");
		Integer result = Integer.valueOf(temp);
		return result == null ? 0 : result;
	}
	
	public int getPropertyForInt(String key, int defaultValue){
		String temp = this.getProperty(key);
		return temp == null ? defaultValue : Integer.valueOf(temp);
	}
}

