package com.training.controller;

import com.training.dto.Content;
import com.training.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {

    @Autowired
     private IContentService service;


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String welcomeIndex(){
        return "welcome";
    }

    @RequestMapping(value = "/webSocketIndex",method = RequestMethod.GET)
    public String webSocketIndex(){
        return "websocket";
    }

    @RequestMapping(value = "index/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Content getContent(@PathVariable("id") Integer id){

        service.insertSelective(new Content());
        return null;
    }



}
