package org.activiti.app.ui;

import org.activiti.app.conf.ApplicationConfiguration;
import org.activiti.app.servlet.ApiDispatcherServletConfiguration;
import org.activiti.app.servlet.AppDispatcherServletConfiguration;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Description
 * User : PpG
 * Date :2020-04-13--13:36
 * @author PpG_yuzf
 */

@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        }
)
@Import({ApplicationConfiguration.class})
public class ActivitiUIApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiUIApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ActivitiUIApplication.class);
    }

    //servlet3.0 支持Java配置
    @Bean
    public ServletRegistrationBean apiDispatcher() {
        DispatcherServlet api = new DispatcherServlet();
        api.setContextClass(AnnotationConfigWebApplicationContext.class);
        api.setContextConfigLocation(ApiDispatcherServletConfiguration.class.getName());
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(api);
        servletRegistrationBean.addUrlMappings("/api/*");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setAsyncSupported(true);
        servletRegistrationBean.setName("api");
        return servletRegistrationBean;
    }
    @Bean
    public ServletRegistrationBean appDispatcher() {
        DispatcherServlet api = new DispatcherServlet();
        api.setContextClass(AnnotationConfigWebApplicationContext.class);
        api.setContextConfigLocation(AppDispatcherServletConfiguration.class.getName());
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(api);
        servletRegistrationBean.addUrlMappings("/app/*");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setAsyncSupported(true);
        servletRegistrationBean.setName("app");
        return servletRegistrationBean;
    }
}
