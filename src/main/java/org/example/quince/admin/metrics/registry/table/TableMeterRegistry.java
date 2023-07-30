package org.example.quince.admin.metrics.registry.table;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import io.micrometer.core.instrument.util.MeterPartition;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author quince
 */
@Slf4j
public class TableMeterRegistry extends StepMeterRegistry {

    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new NamedThreadFactory("table-metrics-publisher");
    private final TableRegistryConfig config;

    public TableMeterRegistry(TableRegistryConfig tableRegistryConfig, Clock clock) {
        super(tableRegistryConfig, clock);
        this.config = tableRegistryConfig;
        start(DEFAULT_THREAD_FACTORY);
    }



    @Override
    protected void publish() {
        log.info("publish-----------");
        for (List<Meter> batch : MeterPartition.partition(this, config.batchSize())) {

            System.out.println(batch.size());
            for (Meter meter : batch) {
                if (meter.getId().getName().equals("table1")) {
                    System.out.println(meter);
                    this.remove(meter);
                }
            }
        }
    }



    @Override
    @NonNull
    protected TimeUnit getBaseTimeUnit() {
        return TimeUnit.SECONDS;
    }

    @Override
    public void start(@NonNull ThreadFactory threadFactory) {
        super.start(threadFactory);
        if (config.enabled()) {
            log.info("Using Table API version {} to write metrics", "v1");
        }
    }
}
