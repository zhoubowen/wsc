package com.controller;

import com.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bowen on 16-4-2上午11:31.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/easyui")
    public String index(){
        return "easyui";
    }
}
