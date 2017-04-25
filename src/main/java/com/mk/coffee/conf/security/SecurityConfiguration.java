package com.mk.coffee.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


/**
 * 该注解和 @Configuration 注解一起使用，
 * 然后，再定义 WebSecurityConfigurer 类型的类或者 继承 WebSecurityConfigurerAdapter类就构成了
 * Spring Security 的配置。 这样，我们就可以配置 用户权限，什么 url 什么人才可以访问等等的一些安全控制
 * Created by Administrator on 2017/2/25
 */

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("simon").password("19961120").roles("USER");
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .httpBasic().disable()
                .csrf().disable();
        http.requestMatchers()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token", "/rest/**", "/api/**", "/**")
                .and()
                .csrf().disable();
    }



}
