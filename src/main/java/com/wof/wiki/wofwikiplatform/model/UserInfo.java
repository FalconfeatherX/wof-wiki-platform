package com.wof.wiki.wofwikiplatform.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserInfo {
    private String username;
    private String password;
    private String role;
    //private Integer timeStamp;
}
