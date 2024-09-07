package org.example.music_box_create_your_music_groupwork.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuration class for web-related settings.
 * Implements WebMvcConfigurer for custom configuration.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * Adds resource handlers for serving static resources.
     * Here, it's configured to serve audio files from the /static/audio/ directory.
     *
     * @param registry the ResourceHandlerRegistry to add the resource handler to.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/audio/audio/**")
                .addResourceLocations("classpath:/static/audio/");
    }

}