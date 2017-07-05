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
        List<Advert> adverts = advertServiceImpl.getAllAdverts();
        model.addAttribute("adverts",adverts);
        return "/admin/adv";
    }
    @GetMapping("/token")
    @ResponseBody
    public String getUploadToken() {
        String token = this.qiniuUploadService.getUploadToken();
        return token;
    }
    @PostMapping("/adv")
    @ResponseBody
    public String addAdv(@RequestBody String str){
        JSONObject json =JSONObject.fromObject(str);
        String url = json.get("url").toString();
        Advert advert=new Advert();
        advert.setUrl(url);
        advertServiceImpl.addAdvert(advert);
        return "1";
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable int id,@RequestBody String str){
        JSONObject json=JSONObject.fromObject(str);
        String fileName=json.get("url").toString();
        advertServiceImpl.deleteAdvert(id);
        qiniuUploadService.removeFile("mijia",fileName);
        return "1";
    }
}
