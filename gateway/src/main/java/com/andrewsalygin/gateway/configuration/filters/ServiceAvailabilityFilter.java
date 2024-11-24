package com.andrewsalygin.gateway.configuration.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.ConnectException;

@Component
public class ServiceAvailabilityFilter extends AbstractGatewayFilterFactory<ServiceAvailabilityFilter.Config> {

    public static class Config {}

    public ServiceAvailabilityFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> chain.filter(exchange)
                .onErrorResume(throwable -> {
                    if (throwable instanceof ConnectException) {
                        exchange.getResponse().setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
                        String errorMessage = "{\"error\": \"Сервис недоступен\"}";
                        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(errorMessage.getBytes())));
                    }
                    return Mono.error(throwable);
                });
    }
}
