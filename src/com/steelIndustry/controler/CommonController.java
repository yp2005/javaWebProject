package com.steelIndustry.controler;

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

@Controller
@RequestMapping("/common")
public class CommonController {
    @RequestMapping(value = "/upload_image", method = RequestMethod.POST)
    public @ResponseBody Object uploadImage(HttpServletRequest request) {
        System.out.println("uploadImage start...");
        Map<String, Object> map = new HashMap<String, Object>();
        if (request.getHeader("content-type") != null
                && "application/x-www-form-urlencoded".equals(request.getHeader("content-type"))) {
            System.out.println("不支持断点续传，直接返回null即可");
            map.put("error", "不支持断点续传");
            return map;
        }
        // 将请求转换成
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Enumeration<String> ps = mRequest.getParameterNames();
        while (ps.hasMoreElements()) {
            String hname = ps.nextElement();
            System.out.println(hname);
            System.out.println(mRequest.getParameter(hname));
        }
        Iterator<String> fns = mRequest.getFileNames();// 获取上传的文件列表
        String fileurl = "";
        while (fns.hasNext()) {
            String s = fns.next();
            System.out.println(s + "===" + mRequest.getFile(s));
            MultipartFile mFile = mRequest.getFile(s);
            if (mFile.isEmpty()) {
                map.put("error", "上传图片为空");
            } else {
                String basePath = "E:\\tmp\\";
                String dPath = "upload";
                File dir = new File(basePath + dPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String originFileName = mFile.getOriginalFilename();
                String suffix = originFileName.split("\\.")[originFileName.split("\\.").length - 1];
                String base64Name = UUID.randomUUID().toString();
                File file = new File(basePath + dPath, base64Name + "." + suffix);
                try {
                    FileUtils.copyInputStreamToFile(mFile.getInputStream(), file);// 存储文件
                    fileurl = "http://192.168.2.61:8888/zqb/upload/" + base64Name + "." + suffix;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // System.out.println(mRequest.getFileNames());
        JSONObject json = new JSONObject();
        json.put("code", "200");
        JSONObject datajson = new JSONObject();
        datajson.put("site_url", fileurl);
        json.put("result_data", datajson);
        // map.put("result", fileurl);//返回结果
        return json;
    }
    
    @RequestMapping(value = "/appupdate", method = RequestMethod.POST)
    public @ResponseBody @SuppressWarnings("all") Object appUpdate() throws Exception {
        JSONObject json = new JSONObject();
        JSONObject ios = new JSONObject();
        ios.put("version", "7.5.1");
        ios.put("title", "锦绣大地APP版本更新");
        ios.put("note", "新增自动升级检测功能\n新增社区聊天功能\n新增推送功能演示页面\n");
        ios.put("url", "itms-apps://itunes.apple.com/cn/app/jin-xiu-da-de-zai-xian/id1077599889?mt=8");
        // ios.put("url", "http://192.168.2.61:8888/jxdd/jxdd.wgt");
        JSONObject android = new JSONObject();
        android.put("version", "7.5.1");
        android.put("title", "锦绣大地APP版本更新");
        android.put("note", "新增自动升级检测功能\n新增社区聊天功能\n新增推送功能演示页面\n");
        android.put("url", "http://192.168.2.61:8888/jxdd/jxdd.apk");
        // android.put("url", "http://192.168.2.61:8888/jxdd/jxdd.wgt");
        json.put("iOS", ios);
        json.put("Android", android);
        return json;
    }
}
