package com.chinalife.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/1/3.
 */
@Controller
public class IndexController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "welcome";
    }
}
