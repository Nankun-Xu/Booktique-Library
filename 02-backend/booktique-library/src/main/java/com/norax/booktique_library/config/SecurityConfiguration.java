package com.norax.booktique_library.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// allows configuring web-based security for specific http requests
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable Cross Site Request Forgery
        http.csrf().disable();

        // Protect endpoints at /api/<type>/secure, requiring authentication for access
        http.authorizeRequests(configurer ->
                        configurer
                                .antMatchers("/api/books/secure/**")
//                                        "/api/reviews/secure/**",
//                                        "/api/messages/secure/**",
//                                        "/api/admin/secure/**")
                                // Configures the application to use OAuth2 resource server capabilities with JWT
                                .authenticated())
                .oauth2ResourceServer()
                .jwt();

        // Add CORS filters
        http.cors();

        // Set a content negotiation strategy based on headers
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // Force a non-empty response body for 401's to make the response friendly
        Okta.configureResourceServer401ResponseBody(http);

        // Build and return the SecurityFilterChain instance
        return http.build();
    }
}
