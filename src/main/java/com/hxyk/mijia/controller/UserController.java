package com.hxyk.mijia.controller;

import com.hxyk.mijia.domain.City;
import com.hxyk.mijia.domain.User;
import com.hxyk.mijia.service.impl.CityServiceImpl;
import com.hxyk.mijia.service.impl.UserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luck on 2017/6/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private CityServiceImpl cityServiceImpl;

    @RequestMapping(value = "/address")
    public String toAddress(Model model,HttpServletRequest request){
        //创建首字母字符串
        String firstWord="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        //拆分成数组
        String[] firstWords=firstWord.split(",");
        //创建总的城市集合
       List cities=new ArrayList();
       //创建首字母集合
       List words=new ArrayList();
       //遍历字幕数组
        for (int i=0;i<firstWords.length;i++) {
            //根据每个字母将城市的集合查询出来
            List<City> cityList =cityServiceImpl.getAllCities(firstWords[i]);
           if (cityList.size()!=0){
               //将按字母查询出来的城市集合存进总的城市集合中
               cities.add(cityList);
               //将每个字母存进字母集合中
               words.add(firstWords[i]);
           }
        }
        //将总的城市集合和字母集合存进model中
        model.addAttribute("cities",cities);
        model.addAttribute("words",words);
        //接受前台传过来注册时已填写的部分信息
        String phone=request.getParameter("phone1");
        String name=request.getParameter("name1");
        String address=request.getParameter("address1");
        String password=request.getParameter("password1");
        String validation=request.getParameter("validation1");
        //创建session对象存放接受的用户信息
        HttpSession session=request.getSession();
        session.setAttribute("phone_back",phone);
        session.setAttribute("name_back",name);
        session.setAttribute("address_back",address);
        session.setAttribute("passwrod_back",password);
        session.setAttribute("validation_back",validation);
        //返回到用户界面
        return "/user/address";
    }

    @RequestMapping("/register")
    public String toRegister(Model model,HttpServletRequest request){
        //获取session中的值
        String phone=(String)request.getSession().getAttribute("phone_back");
        String name=(String)request.getSession().getAttribute("name_back");
        String address=(String)request.getSession().getAttribute("address_back");
        String password=(String)request.getSession().getAttribute("password_back");
        String validation=(String)request.getSession().getAttribute("validation_back");
        String city=request.getParameter("city");
        //再将获取的值存进model中
        model.addAttribute("phone2",phone);
        model.addAttribute("name2",name);
        model.addAttribute("address2",address);
        model.addAttribute("password2",password);
        model.addAttribute("validation2",validation);
        model.addAttribute("city2",city);
        //跳转到register页面
        return "/user/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String isRegister(@RequestBody String string,HttpServletRequest request){
        //获取前台ajax中的data数据
        //使用JSON对象转化接受的数据
        JSONObject jsonObject = JSONObject.fromObject(string);
        //是用get和toString 方法将json对象转化成字符串
        String phone=jsonObject.get("phone").toString();
        String name=jsonObject.get("name").toString();
        String city=jsonObject.get("city").toString();
        String address=jsonObject.get("address").toString();
        String password=jsonObject.get("password").toString();
        //清除session缓存的值
        HttpSession session=request.getSession();
        session.removeAttribute("phone_back");
        session.removeAttribute("name_back");
        session.removeAttribute("address_back");
        session.removeAttribute("password_back");
        session.removeAttribute("validation_back");
        //创建user对象
        User user=new User();
        user.setPhoneNumber(phone);
        user.setRealName(name);
        user.setCity(city);
        user.setAddress(address);
        user.setPassword(password);
        //添加用户
        userServiceImpl.addUser(user);
        //返回用户新到前台页面
        return user.toString();
    }

    //跳转到登录页面
    @GetMapping("/login")
    public String login(){
        return "/user/login";
    }

    //用户登录
    @PostMapping("/login")
    @ResponseBody
    public  String login( @RequestBody String str,HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接受ajax的data 数据转化成json对象
        JSONObject json =JSONObject.fromObject(str);
        //将json转换成字符串
        String login=json.get("login").toString();
        String password=json.get("password").toString();
        //登录方法
        int isLogin=userServiceImpl.login(login,password);
        if (isLogin==1){
            //手机号不存在返回1
            return "1";
        }else if (isLogin==2){
            //手机号、密码正确。
            // 根据手机号创建用户
            User user=userServiceImpl.getUserByPhone(login);
            //将用户对象存在session中
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            //验证成功返回2
            return "2";
        }else {
            //其他情况返回3
            return "3";
        }
    }

    //跳转到找回密码页面
    @GetMapping("/find")
    public String toFind(){
        return "/user/findpassword";
    }

    //找回密码
    @PostMapping("/find")
    @ResponseBody
    public String findPassworf(@RequestBody String str){
        //接受ajax的data数据，转换成json对象
        JSONObject json =JSONObject.fromObject(str);
        //将json转换成字符串
        String phone=json.get("phone").toString();
        String password=json.get("password").toString();
        //根据手机号串讲用户
        User user=userServiceImpl.getUserByPhone(phone);
        if (user==null){
            //用户不存在，手机号不正确，返回0
            return "0";
        }else {
            //手机号正确，将新密码赋值给用户
            user.setPassword(password);
            //修改用户密码
            userServiceImpl.updataUser(user);
            //返回1
            return "1";
        }
    }
}
