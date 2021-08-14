package com.wof.wiki.wofwikiplatform.mapper;

import com.wof.wiki.wofwikiplatform.model.CharBoxInfo;
import com.wof.wiki.wofwikiplatform.model.CharInfo;
import com.wof.wiki.wofwikiplatform.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface CharInfoMapper {
    @Select("SELECT * from quotation where name = #{name}")
    CharInfo getQuotation(String name);

    @Select("SELECT * from msgbox where cname = #{cname}")
    CharBoxInfo getCharacterBoxInfo(String cname);

}
