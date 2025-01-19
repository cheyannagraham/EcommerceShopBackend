package learn.demofsecommerceapp.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig{
    @Value("${app.consumer}")
    private String consumer;

    @Bean
    CorsFilter corsFilter() {
        System.out.println("CONSUMER: " + consumer);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(consumer));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
