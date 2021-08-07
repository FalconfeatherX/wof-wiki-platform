package com.wof.wiki.wofwikiplatform.config;

import com.wof.wiki.wofwikiplatform.filter.LogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.beans.Expression;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WofAuthenticationProvider wofAuthenticationProvider;

    @Bean
    /**
     * @Description Spring5要求密码必须加密
     * @Param
     * @return
     **/
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    //authenticationProvider可自定义密码匹配规则，是有自带的实现的，这只是一个接口
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(wofAuthenticationProvider);
    }

    @Override
    //添加权限，登陆访问限制规则
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers().permitAll()
                    .antMatchers("/edit/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
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
