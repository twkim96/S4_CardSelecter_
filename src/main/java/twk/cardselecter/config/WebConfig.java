package twk.cardselecter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String uploadPath = "/upload/**";
        String resourcePath = "file:///Users/twkim/Documents/server/";
        registry.addResourceHandler(uploadPath)
                .addResourceLocations(resourcePath);
    }
}
