package org.example.quince.admin.metrics.health;


import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author quince
 */
@Component
@Data
public class HealthMetrics implements MeterBinder {

    /**
     * 100  up
     * 0  down
     * 0 unknown
     */
    private Integer health = 100;


    @Override
    public void bindTo(MeterRegistry registry) {
        Gauge.builder("health", () -> health)
                .register(registry);
    }
}
