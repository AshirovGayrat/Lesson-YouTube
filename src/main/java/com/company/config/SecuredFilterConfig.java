package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {

    @Autowired
    private JwtFilter jwtTokenFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(jwtTokenFilter);
        bean.addUrlPatterns("/profile/adm/*");
        bean.addUrlPatterns("/profile/public/*");
        bean.addUrlPatterns("/profile/image/*");
        bean.addUrlPatterns("/email/adm/*");
        bean.addUrlPatterns("/category/adm/*");
        bean.addUrlPatterns("/channel/adm/*");
        bean.addUrlPatterns("/channel/public/*");
        bean.addUrlPatterns("/playlist/public/*");
        bean.addUrlPatterns("/attach/public/*");
        bean.addUrlPatterns("/attach/adm/*");
        bean.addUrlPatterns("/category/adm/*");
        bean.addUrlPatterns("/video/adm/*");
        bean.addUrlPatterns("/video/public/*");
        bean.addUrlPatterns("/video_like/public/*");
        bean.addUrlPatterns("/video_like/adm/*");
        bean.addUrlPatterns("/playlist_video/adm/*");
        bean.addUrlPatterns("/playlist_video/public/*");
        bean.addUrlPatterns("/comment/adm/*");
        bean.addUrlPatterns("/comment/public/*");
        bean.addUrlPatterns("/subscription/adm/*");
        bean.addUrlPatterns("/subscription/public/*");
        return bean;
    }

}
