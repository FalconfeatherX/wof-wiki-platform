package com.wof.wiki.wofwikiplatform.config;

import com.wof.wiki.wofwikiplatform.filter.LogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


@Configuration
@EnableResourceServer
@ConditionalOnMissingBean(type = "com.wof.wiki.wofwikiplatform.config.SecurityConfig")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;

    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 资源处理配置
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore).authenticationEntryPoint(authenticationEntryPoint);
    }

    /**
     * 过滤链配置
     * @param http
     */
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/edit/**").hasRole("ADMIN")
                .and()
                .addFilterBefore(new LogFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                //这个/login是自带的中转处理方法，默认参数username和password
                .loginProcessingUrl("/login")
                .and().httpBasic();
        //.loginPage("/login").permitAll();
        //.successHandler()
    }
}
