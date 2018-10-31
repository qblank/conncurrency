package cn.qblank.concurrency;

import cn.qblank.concurrency.filter.HttpFilter;
import cn.qblank.concurrency.intercepter.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.logging.Filter;

@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    /**
     * 配置过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean httpFilter(){
        FilterRegistrationBean registerationBean = new FilterRegistrationBean();
        registerationBean.setFilter(new HttpFilter());
        registerationBean.addUrlPatterns("/threadLocal/*");
        return registerationBean;
    }

    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
