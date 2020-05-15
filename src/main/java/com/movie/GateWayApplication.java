package com.movie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.spring4all.swagger.EnableSwagger2Doc;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableSwagger2Doc
public class GateWayApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
    
    @Primary
    @Component
    class DocumentationConfig implements SwaggerResourcesProvider {
        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> swaggerResourceList = new ArrayList<>();
            swaggerResourceList.add(createSwaggerResource("MovieDoc", "/api/movie-doc/v2/api-docs ", "1.0"));
            swaggerResourceList.add(createSwaggerResource("OrderDoc", "/api/order-doc/v2/api-docs ", "1.0"));
            swaggerResourceList.add(createSwaggerResource("UserDoc", "/api/user-doc/v2/api-docs ", "1.0"));
            return swaggerResourceList;
        }
        
        private SwaggerResource createSwaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }
}
