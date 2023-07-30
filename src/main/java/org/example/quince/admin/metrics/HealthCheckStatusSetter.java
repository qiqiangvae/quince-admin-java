package org.example.quince.admin.metrics;

import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author quince
 */
@Component
public class HealthCheckStatusSetter extends AbstractHealthCheckStatusSetter {
    private final HealthEndpoint healthEndpoint;

    public HealthCheckStatusSetter(HealthMetrics healthMetrics, HealthEndpoint healthEndpoint) {
        super(healthMetrics);
        this.healthEndpoint = healthEndpoint;
    }


    @Override
    public void setHealthStatus(HealthMetrics healthMetrics) {
        healthMetrics.setHealth(new Random().nextInt(0, 100));
//        HealthComponent health = healthEndpoint.health();
//        if (health != null) {
//            Status status = health.getStatus();
//            switch (status.getCode()) {
//                case "UP": {
//                    healthMetrics.setHealth(100);
//                    break;
//                }
//                case "DOWN":
//                    ;
//                case "UNKNOWN":
//                    ;
//                default: {
//                    healthMetrics.setHealth(0);
//                    break;
//                }
//            }
    }


}
