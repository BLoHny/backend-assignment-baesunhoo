package kr.co.polycube.backendtest.global.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<UrlFilter> urlFilter() {
        FilterRegistrationBean<UrlFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new UrlFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
