package io.github.md678685.runway.config;

import freemarker.template.TemplateException;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates");
        try {
            freeMarkerConfigurer.afterPropertiesSet();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        freeMarkerConfigurer.getConfiguration().setOutputEncoding("UTF-8");
        freeMarkerConfigurer.getConfiguration().setLogTemplateExceptions(false);
        freeMarkerConfigurer.getConfiguration().setTemplateExceptionHandler((te, env, out) -> {
            String message = te.getMessage();
            if (message.contains("org.springframework.web.servlet.support.RequestContext.getMessage")) {
                System.out.println("[Template Error, most likely missing key] " + message);
            } else if (message.contains(" see cause exception in the Java stack trace.")) {
                te.printStackTrace();
            } else {
                System.out.println("[Template Error] " + message);
            }
        });
        return freeMarkerConfigurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/public/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .resourceChain(true)
                .addResolver(new VersionResourceResolver());

        registry
                .addResourceHandler("/lib/**")
                .addResourceLocations("/webjars/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .resourceChain(true)
                .addResolver(new VersionResourceResolver());
    }

    @Bean
    public ErrorViewResolver errorViewResolver() {
        return (request, status, model) -> {
            if (status == HttpStatus.GATEWAY_TIMEOUT || status == HttpStatus.REQUEST_TIMEOUT) {
                return new ModelAndView("errors/timeout");
            } else if (status == HttpStatus.NOT_FOUND) {
                return new ModelAndView("errors/notFound");
            } else {
                return new ModelAndView("errors/error");
            }
        };
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }
}
