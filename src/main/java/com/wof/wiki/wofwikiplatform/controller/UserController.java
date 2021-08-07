package com.wof.wiki.wofwikiplatform.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/userlist")
    public Map<String, Object> getUserList() {
        return new HashMap<>() {{
            put("Name", "Darkstalker");
            put("Ability", "Animus");
            put("Tribe", "NightWing");
        }};
    }

    @GetMapping("/login")
    public Map<String, Object> login() {
            return new HashMap<String, Object>() {{
                put("code", 200);
                put("msg", "登录成功");
            }};
        }
}