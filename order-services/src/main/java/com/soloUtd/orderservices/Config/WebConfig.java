package com.soloUtd.orderservices.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Component
public class WebConfig {

    @Bean
    @Qualifier(value = "webClient")
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }
}
