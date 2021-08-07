package com.wof.wiki.wofwikiplatform.service;

import com.wof.wiki.wofwikiplatform.mapper.UserInfoMapper;
import com.wof.wiki.wofwikiplatform.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
/**
UserDetailsService具体实现，包括一个方法用于加载用户信息并且封装到安全容器中，这里与数据库交互
UserDetails返回一个User类，User类实现了UserDetails，是自带的实现，也可以根据需要添加如手机号之类的信息构成自定义User
*/
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username){
        UserInfo userInfo = userInfoMapper.getPasswordByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("未找到用户");
        }
        //System.out.println("found");
        //ROLE_指角色而不是权限
        return new User(userInfo.getUsername(),userInfo.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+userInfo.getRole()));
    }
}
