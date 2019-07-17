package com.training.controller;

import com.training.dto.UserInfo;
import com.training.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public UserInfo  getUserInfo(@PathVariable("id") Integer id){
        UserInfo userInfo = userService.findUserById(id);
        return userInfo;
    }
    @RequestMapping(value ="user/list",method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfo> getUserInfoList(){
        return userService.getUserInfoList();
    }

    @RequestMapping(value ="user/add",method = RequestMethod.GET)
    public void add(UserInfo userInfo){
        userService.add(userInfo);
    }

    @RequestMapping(value ="user/delete",method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(@RequestParam(value = "id") Integer id){
        userService.delete(id);
        return true;
    }
    @RequestMapping(value ="user/update",method = RequestMethod.GET)
    public void update(UserInfo userInfo){
        userService.update(userInfo);
    }
}
