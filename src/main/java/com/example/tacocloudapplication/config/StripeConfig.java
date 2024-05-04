package com.example.tacocloudapplication.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class StripeConfig {
    @Value("${stripe.api.key}")
    private String secretApiKey;

    @PostConstruct
    public void setup() {
        Stripe.apiKey = secretApiKey;
    }
}
