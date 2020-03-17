package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhanghaoyang
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService; // 除非你拿静态工厂去造 否则这玩意得是非static区域的

    @GetMapping("/login")
    public String login(String username, String password,HttpSession sess) {
        if (username == null || password == null || "".equals(username) || "".equals(password)) {
            return "{state:200,msg:'request parameter failed'}";
        } else {
            if (userService.login(username, password)) {
                sess.setAttribute("username", username);
                return "{state:200,msg:'login success'}";
            } else {
                return "{state:200,msg:'authorize error'}";
            }
        }
    }

    @PostMapping("/register")
    public String registerUser(HttpServletRequest req, String username, String password) {
        if (username == null || password == null || "".equals(username) || "".equals(password)) {
            return "{state:200,msg:'request parameter failed'}";
        } else {
            try {
                userService.register(username, password);
                return "{state:200,msg:'register success'}";
            } catch (Exception e) {
                return "{state:200,msg:'register error'}";
            }
        }
    }
}
