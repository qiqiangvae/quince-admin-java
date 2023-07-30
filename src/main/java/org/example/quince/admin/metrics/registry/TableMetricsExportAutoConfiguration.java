package org.example.quince.admin.metrics.registry;

import io.micrometer.core.instrument.Clock;
import org.example.quince.admin.metrics.TableMetricsPusher;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.ConditionalOnEnabledMetricsExport;
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author quince
 */
@AutoConfiguration(
        before = {CompositeMeterRegistryAutoConfiguration.class, SimpleMetricsExportAutoConfiguration.class, TableMetricsPusher.class},
        after = MetricsAutoConfiguration.class)
@ConditionalOnBean(Clock.class)
@ConditionalOnClass(TableMeterRegistry.class)
@ConditionalOnEnabledMetricsExport("table")
@EnableConfigurationProperties(TableProperties.class)
public class TableMetricsExportAutoConfiguration {

    private final TableProperties properties;

    public TableMetricsExportAutoConfiguration(TableProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public TableRegistryConfig tableRegistryConfig() {
        return new TablePropertiesConfigAdapter(this.properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public TableMeterRegistry tableMeterRegistry(TableRegistryConfig influxConfig, Clock clock) {
        return new TableMeterRegistry(influxConfig, clock);
    }

}