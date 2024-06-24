package org.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring3.SpringTemplateEngine;
// import org.thymeleaf.spring3.templateresolver.SpringResourceTemplateResolver;
// import org.thymeleaf.templatemode.TemplateMode;

import nz.net.ultraq.web.thymeleaf.LayoutDialect;

@Configuration
public class Thymeleafconfig {

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }
}
