package com.happysoul.home.config;

import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 
 * Druid数据源网页访问配置
 * @author fuhongsheng
 *
 */
@Configuration
public class DruidConfig {
	
	private static final ResourceBundle DRUID = ResourceBundle.getBundle("druid");
	
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }
	
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
		if(DRUID.containsKey("allow") && !DRUID.getString("allow").trim().equals("")) {
			servletRegistrationBean.addInitParameter("allow",DRUID.getString("allow"));//访问白名单
		}else {
			servletRegistrationBean.addInitParameter("allow","127.0.0.1");//访问白名单
		}
		servletRegistrationBean.addInitParameter("loginUsername", DRUID.getString("loginUsername"));
		servletRegistrationBean.addInitParameter("loginPassword", DRUID.getString("loginPassword"));
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
		filterRegistrationBean.setName("DruidWebStatFilter");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}