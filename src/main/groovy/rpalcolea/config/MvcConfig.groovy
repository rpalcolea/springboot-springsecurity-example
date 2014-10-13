package rpalcolea.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home")
        registry.addViewController("/login").setViewName("login")
        registry.addViewController("/panel").setViewName("panel")
        registry.addViewController("/userHome").setViewName("userHome")
    }
}