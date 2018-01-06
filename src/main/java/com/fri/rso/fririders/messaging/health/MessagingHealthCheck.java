package com.fri.rso.fririders.messaging.health;

import com.fri.rso.fririders.messaging.config.ConfigProperties;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.inject.Inject;

public class MessagingHealthCheck implements HealthCheck{
    @Inject
    private ConfigProperties configProperties;

    @Override
    public HealthCheckResponse call() {

        if (configProperties.isHealthy()) {
            return HealthCheckResponse.named(MessagingHealthCheck.class.getSimpleName()).up().build();
        } else {
            return HealthCheckResponse.named(MessagingHealthCheck.class.getSimpleName()).down().build();
        }

    }
}
