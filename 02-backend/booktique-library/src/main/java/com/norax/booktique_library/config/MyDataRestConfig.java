package com.norax.booktique_library.config;

import com.norax.booktique_library.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Configuration class for customizing the behavior of Spring Data REST.
 * Spring Data REST automatically exposes repositories as REST endpoints.
 */
@Configuration // Indicates that this class contains Spring configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    // Allowed origins for CORS (Cross-Origin Resource Sharing)
    private String theAllowedOrigins = "http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Define the HTTP methods that should be unsupported
        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
                HttpMethod.PUT
        };

        // Expose entity IDs in the JSON response
        config.exposeIdsFor(Book.class);

        // Disable HTTP methods for Book entity: POST, PATCH, DELETE, PUT
        disableHttpMethods(Book.class, config, theUnsupportedActions);

        // Configure CORS mapping to allow requests from the specified origins
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);
    }

    /**
     * Helper method to disable specified HTTP methods for a given entity class.
     *
     * @param theClass the entity class for which the methods are to be disabled
     * @param config the repository rest configuration
     * @param theUnsupportedActions the HTTP methods to be disabled
     */
    private void disableHttpMethods(Class<?> theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions));
    }
}
