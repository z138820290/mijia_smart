package com.hxyk.mijia.controller;

import com.hxyk.mijia.domain.Advert;
import com.hxyk.mijia.domain.Area;
import com.hxyk.mijia.domain.City;
import com.hxyk.mijia.service.impl.AdvertServiceImpl;
import com.hxyk.mijia.service.impl.AreaServiceImpl;
import com.hxyk.mijia.service.impl.CityServiceImpl;
import com.hxyk.mijia.utils.QiniuUploadServiceImpl;
import groovy.util.logging.Slf4j;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private CityServiceImpl cityServiceImpl;
    @Autowired
    private AreaServiceImpl areaServiceImpl;
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
        System.out.println(str);
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
    @PutMapping("/adv/{id}")
    @ResponseBody
    public String updateAdv(@RequestBody String str, @PathVariable int id){
        //将ajax对象传入对data转换成json对象
        JSONObject json =JSONObject.fromObject(str);
        //获取七牛上存储广告的url地址
        String url = json.get("url").toString();
        //创建广告对象
        Advert advert=new Advert();
        advert.setUrl(url);
        advert.setAdId(id);

        //向数据库中加入广告信息
        advertServiceImpl.updateAdvert(advert);
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
    @GetMapping("/addCity")
    public String toAddCity(){
        return "/admin/addcity";
    }
    @PostMapping("/addCity")
    public String addCity(HttpServletRequest request){
        //获取表单中城市和小区名字
        String cityName=request.getParameter("city");
        String areaName=request.getParameter("area");
        //创建城市对象并赋值
        City city=new City();
        city.setCityName(cityName);
        //添加城市
        cityServiceImpl.addCity(city);
        //根据城市名称重新获取城市对象
        city=cityServiceImpl.getCityByName(cityName);
        //创建小区对象，并赋值
        Area area=new Area();
        area.setAreaName(areaName);
        area.setCity(city);
        //添加小区对象
        areaServiceImpl.addArea(area);
        return "/admin/addcity";
    }
}
