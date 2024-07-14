package com.project.spring_cloud_gateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfig {

    @Bean
    public RouteLocator getGatewayRoute(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p-> p.path("/get")
                        .filters(gatewayFilterSpec-> gatewayFilterSpec
                                .addRequestHeader("MyHeader","Added Header My URI")
                                .addRequestParameter("param","myParamValue")
                        )
                        .uri("http://httpbin.org:80"))
                .route(p->p.path("/company/**")
                        .uri("lb://COMPANYMICROSERVICE")) // here lb is for load balancing
                .route(p->p.path("/jobs/**")
                        .uri("lb://JOBMICROSERVICE"))
                .route(p->p.path("/reviews/**")
                        .uri("lb://REVIEWMICROSERVICE"))
                .build();
    }
}
