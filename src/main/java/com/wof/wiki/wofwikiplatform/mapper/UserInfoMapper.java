package com.wof.wiki.wofwikiplatform.mapper;


import com.wof.wiki.wofwikiplatform.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper {

    @Select("select * from user where username = #{username}")
    UserInfo getPasswordByUsername(String username);



}
