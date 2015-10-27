package jmetermeasure;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class JMeterMeasureJerseyConfig extends ResourceConfig {

    public JMeterMeasureJerseyConfig() {
        register(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        register(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        register(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        register(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);

        registerSpartacusResources();
    }

    private void registerSpartacusResources() {
    	// put resources over here
    }
}
