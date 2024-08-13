package org.example.music_box_create_your_music_groupwork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig class configures the web application context.
 * It implements the WebMvcConfigurer interface to customize the default Spring MVC configuration.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures the resource handlers for serving static resources.
     *
     * @param registry the ResourceHandlerRegistry to add resource handlers to
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/audio/audio/**")
                .addResourceLocations("classpath:/static/audio/");
    }

}