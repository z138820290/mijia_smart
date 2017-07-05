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

    @GetMapping("/")
    public String toWelcome(){
        return "welcome";
    }
    @GetMapping("/index")
    public String toIndex(Model model){
        List<Advert> adverts = advertServiceImpl.getAllAdverts();
        int i=0;
        List points=new ArrayList();
        for (Advert advert:adverts) {
            i++;
            System.out.println();
            points.add(i);

        }
        model.addAttribute("adverts",adverts);
        model.addAttribute("points",points);
        return "index";
    }

}
