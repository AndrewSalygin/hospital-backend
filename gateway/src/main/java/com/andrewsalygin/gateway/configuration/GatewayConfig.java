package com.andrewsalygin.gateway.configuration;

import com.andrewsalygin.gateway.configuration.filters.ServiceAvailabilityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final ServiceAvailabilityFilter serviceAvailabilityFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("application_service", r -> r
                .path("/patients/**", "/admin-patients/**", "/super-admin-patients/**",
                    "/doctors/**", "/admin-doctors/**", "/super-admin-doctors/**",
                    "/recipes/**", "/super-admin-recipes/**", "/diseases/**",
                    "/super-admin-diseases/**", "/medications/**", "/admin-medications/**",
                    "/journal-patient/**", "/admin-journal-patient/**", "/treatments/**",
                    "/medicalProcedures/**", "/super-admin-medicalProcedures/**",
                    "/doctorsMedicalProcedures/**", "/super-admin-doctorsMedicalProcedures/**",
                    "/surgeries/**", "/admin-surgeries/**")
                .filters(f -> f.filter(serviceAvailabilityFilter.apply(new ServiceAvailabilityFilter.Config())))
                .uri("lb://application-service"))
            .route("specialization_service", r -> r.path("/specializations/**")
                .or().path("/super-admin-specializations/**")
                .filters(f -> f.filter(serviceAvailabilityFilter.apply(new ServiceAvailabilityFilter.Config())))
                .uri("lb://specialization-service"))
            .build();
    }
}
