package com.wof.wiki.wofwikiplatform.controller;


import com.wof.wiki.wofwikiplatform.mapper.CharInfoMapper;
import com.wof.wiki.wofwikiplatform.mapper.UserInfoMapper;
import com.wof.wiki.wofwikiplatform.model.CharInfo;
import com.wof.wiki.wofwikiplatform.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    CharInfoMapper charInfoMapper;

    @RequestMapping("/edit")
    public CharInfo login(@RequestParam String cname){

        return charInfoMapper.getQuotation(cname);
    }
}
