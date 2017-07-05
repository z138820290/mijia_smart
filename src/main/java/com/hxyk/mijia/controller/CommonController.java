package com.hxyk.mijia.controller;

import com.hxyk.mijia.domain.Advert;
import com.hxyk.mijia.domain.City;
import com.hxyk.mijia.service.CityService;
import com.hxyk.mijia.service.impl.AdvertServiceImpl;
import com.hxyk.mijia.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luck on 2017/6/26.
 */
@Controller
@RequestMapping("")
public class CommonController {
    @Autowired
    private AdvertServiceImpl advertServiceImpl;

    //欢迎页面
    @GetMapping("/")
    public String toWelcome(){
        return "welcome";
    }
    //首页
    @GetMapping("/index")
    public String toIndex(Model model){
        //获取所有轮播广告
        List<Advert> adverts = advertServiceImpl.getAllAdverts();
        int i=0;
        //轮播焦点
        List points=new ArrayList();
        for (Advert advert:adverts) {
            i++;
            points.add(i);

        }
        model.addAttribute("adverts",adverts);
        model.addAttribute("points",points);
        return "index";
    }

}
