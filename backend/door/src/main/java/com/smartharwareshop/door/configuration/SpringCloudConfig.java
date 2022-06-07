package com.smartharwareshop.door.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
  /**
   * Static routes due to below issue mentioned i comments
   * @param builder
   * @return
   */
  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
      .route("userModule", r -> r.path("/user-service/**")
        .uri("http://localhost:8083"))

      .route("cartModuler",r  -> r.path("/cart-service/**")
        .uri("http://localhost:8082"))

      .route("inventoryModule", r -> r.path("/inventory-service/**")
        .uri("http://localhost:8081"))

      .route("uimodule", r -> r.path("/shop/**")
        .uri("http://localhost:4200"))
      .build();
  }
  /**
   * Dynamic configuration had some issue, services went unavailable randomly
   */
//  @Bean
//  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//    return builder.routes()
//      .route("userModule", r -> r.path("/user-service/**")
//        .uri("lb://USER-SERVICE"))
//
//      .route("cartModuler",r  -> r.path("/cart-service/**")
//        .uri("lb://CART-SERVICE"))
//
//      .route("inventoryModule", r -> r.path("/inventory-service/**")
//        .uri("lb://INVENTORY-SERVICE"))
//      .build();
//  }

}
