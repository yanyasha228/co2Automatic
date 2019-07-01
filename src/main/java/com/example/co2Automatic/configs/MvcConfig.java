package com.example.co2Automatic.configs;

import com.example.co2Automatic.HelpUtils.CustomHandlerMethodArgumentResolvers.OrderHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(new OrderHandlerMethodArgumentResolver());
//    }

    //    @Bean
//    @Description("Thymeleaf template resolver serving HTML 5")
//    public ITemplateResolver templateResolver() {
//
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//
//
//        templateResolver.setPrefix("classpath:/templates/");
//        templateResolver.setCacheable(false);
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        templateResolver.setCharacterEncoding("UTF-8");
//
//        return templateResolver;
//    }
//
//    @Bean
//    @Description("Thymeleaf template engine with Spring integration")
//    public SpringTemplateEngine templateEngine() {
//
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//
//        return templateEngine;
//    }
//
//    @Bean
//    @Description("Thymeleaf view resolver")
//    public ViewResolver viewResolver() {
//
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setCharacterEncoding("UTF-8");
//
//        return viewResolver;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
