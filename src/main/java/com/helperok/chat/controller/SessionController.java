package com.helperok.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/im")
public class SessionController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Map test(){
        Map<String, Object> result = new HashMap<>();
        result.put("state", 200);
        return result;
    }
}
