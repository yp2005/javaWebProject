package com.steelIndustry.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.steelIndustry.bo.AjaxResult;
import com.steelIndustry.util.CommonProperties;

@Controller
@RequestMapping("/common")
public class CommonController {
    @RequestMapping(value = "/upload_image", method = RequestMethod.POST)
    public @ResponseBody AjaxResult uploadImage(HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        if (request.getHeader("content-type") != null
                && "application/x-www-form-urlencoded".equals(request.getHeader("content-type"))) {
            result.setErroCode(3000);
            result.setErroMsg("不支持断点续传");
            return result;
        }
        // 将请求转换成
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fns = mRequest.getFileNames();// 获取上传的文件列表
        String fileurl = "";
        while (fns.hasNext()) {
            String s = fns.next();
            MultipartFile mFile = mRequest.getFile(s);
            if (mFile.isEmpty()) {
                result.setErroCode(3000);
                result.setErroMsg("上传图片为空");
            } else {
                String uploadImgPath = CommonProperties.getInstance().getProperty("uploadImgPath");
                String imgServer = CommonProperties.getInstance().getProperty("imgServer");
                File dir = new File(uploadImgPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String originFileName = mFile.getOriginalFilename();
                String suffix = originFileName.split("\\.")[originFileName.split("\\.").length - 1];
                String base64Name = UUID.randomUUID().toString();
                File file = new File(uploadImgPath, base64Name + "." + suffix);
                try {
                    FileUtils.copyInputStreamToFile(mFile.getInputStream(), file);
                    fileurl = imgServer + base64Name + "." + suffix;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        result.setErroCode(2000);
        result.setErroMsg("");
        JSONObject datajson = new JSONObject();
        datajson.put("site_url", fileurl);
        result.setResult(datajson);
        return result;
    }
    
    @RequestMapping(value = "/appupdate", method = RequestMethod.POST)
    public @ResponseBody AjaxResult appUpdate() {
        AjaxResult result = new AjaxResult();
        JSONObject json = new JSONObject();
        JSONObject ios = new JSONObject();
        ios.put("version", "");
        ios.put("title", "");
        ios.put("note", "");
        ios.put("url", "");
        JSONObject android = new JSONObject();
        android.put("version", "");
        android.put("title", "");
        android.put("note", "");
        android.put("url", "");
        json.put("iOS", ios);
        json.put("Android", android);
        result.setErroCode(2000);
        result.setErroMsg("");
        result.setResult(json);
        return result;
    }
}
