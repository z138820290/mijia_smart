package com.hxyk.mijia.controller;

import com.hxyk.mijia.domain.Advert;
import com.hxyk.mijia.service.impl.AdvertServiceImpl;
import com.hxyk.mijia.utils.QiniuUploadServiceImpl;
import groovy.util.logging.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luck on 2017/7/2.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdvertServiceImpl advertServiceImpl;
    @Autowired
    private QiniuUploadServiceImpl qiniuUploadService;
    //后台首页
    @GetMapping("/index")
    public String toIndex(){
        return "/admin/index";
    }

    //广告轮播页面
    @GetMapping("/adv")
    public String toAdv(Model model){
        //获取所有轮播广告
        List<Advert> adverts = advertServiceImpl.getAllAdverts();
        //存入model
        model.addAttribute("adverts",adverts);
        return "/admin/adv";
    }
    //七牛获取token
    @GetMapping("/token")
    @ResponseBody
    public String getUploadToken() {
        String token = this.qiniuUploadService.getUploadToken();
        return token;
    }
    //后台轮播设置
    @PostMapping("/adv")
    @ResponseBody
    public String addAdv(@RequestBody String str){
        //将ajax对象传入对data转换成json对象
        JSONObject json =JSONObject.fromObject(str);
        //获取七牛上存储广告的url地址
        String url = json.get("url").toString();
        //创建刚搞对象
        Advert advert=new Advert();
        advert.setUrl(url);
        //向数据库中加入广告信息
        advertServiceImpl.addAdvert(advert);
        return "1";
    }
    //删除广告
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable int id,@RequestBody String str){
        //获取ajax数据转换成json
        JSONObject json=JSONObject.fromObject(str);
        String fileName=json.get("url").toString();
        //删除数据库中的广告
        advertServiceImpl.deleteAdvert(id);
        //删除七牛空间里的广告
        qiniuUploadService.removeFile("mijia",fileName);
        return "1";
    }
}
