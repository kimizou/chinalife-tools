package com.chinalife.tools.controller;

import com.chinalife.tools.web.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2016/1/24.
 */
@Controller
public class UploadController {
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public WebResult<Object> upload(MultipartFile file) {
        return new WebResult<Object>();
    }
}
