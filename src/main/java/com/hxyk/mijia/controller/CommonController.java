package com.hxyk.mijia.controller;

import com.hxyk.mijia.domain.City;
import com.hxyk.mijia.service.CityService;
import com.hxyk.mijia.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Luck on 2017/6/26.
 */
@Controller
@RequestMapping("")
public class CommonController {
    @Autowired
    private CityServiceImpl cityServiceImpl;

    @GetMapping("/")
    public String toWelcome(){
        return "welcome";
    }
    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }
    @GetMapping("/address")
    public String chooseAddress(){
        List<City> cities = cityServiceImpl.getAllCities("C");
        for (City city:cities) {

        }
        return "index";
    }
}
