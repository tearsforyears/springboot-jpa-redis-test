package com.example.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghaoyang
 */
@RestController
public class MsgController {
    @GetMapping("/msg")
    public String msg(String msg, String type) {
        if ("forward".equals(type)) {
            return msg;
        } else {
            return "{state:200,msg:'error'}";
        }
    }
}
