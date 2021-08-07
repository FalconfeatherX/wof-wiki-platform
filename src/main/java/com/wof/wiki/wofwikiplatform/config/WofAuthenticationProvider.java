package com.wof.wiki.wofwikiplatform.config;

import com.wof.wiki.wofwikiplatform.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


/**
 *AuthenticationManager维护一个列表，每次认证时循环其中的AuthenticationProvider类型列表
 *AuthenticationProvider具体实现，其包括两个方法
 *所谓AuthenticationProvider就是指一种认证方法，这里用的就是用户名密码的方式，还有邮箱，第三方登陆之类的
 **/
@Component
public class WofAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    //authenticate实现了自定义认证方法
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        boolean flag = bCryptPasswordEncoder.matches(password,new BCryptPasswordEncoder().encode(userDetails.getPassword()));

        if (flag) {
            return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
        }
        throw new AuthenticationException("密码错误") {
        };

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


}
