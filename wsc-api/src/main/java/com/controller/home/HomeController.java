package com.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页
 * Created by bowen on 2016-11-06 13:17
 */
@Controller
public class HomeController {

    @ResponseBody
    @RequestMapping("/home/index")
    public String index(){
        return "Hello index...";
    }
}
