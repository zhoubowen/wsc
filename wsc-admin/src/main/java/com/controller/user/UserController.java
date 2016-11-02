package com.controller.user;


import com.common.entity.user.User;
import com.common.service.user.IUserService;
import com.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * Created by bowen on 16-4-2上午11:05.
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {

    @Autowired
    private IUserService IUserService;

    @RequestMapping("list")
    public String list(Model model){
        Collection<User> list = IUserService.getAllUsers();
        model.addAttribute("dataList", list);
        return "users";
    }
}
