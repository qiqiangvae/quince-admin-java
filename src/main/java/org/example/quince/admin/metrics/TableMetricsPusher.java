package org.example.quince.admin.metrics;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.example.quince.admin.metrics.TableMetrics;
import org.example.quince.admin.metrics.registry.TableMeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.export.ConditionalOnEnabledMetricsExport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author quince
 */
@Component
@RequiredArgsConstructor
@ConditionalOnEnabledMetricsExport("table")
public class TableMetricsPusher {
    static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    private static final Random RANDOM = new Random();

    private final TableMeterRegistry tableMeterRegistry;

    /**
     * 定时更新 health 统计.
     */
    @PostConstruct
    void doSet() {
        scheduledExecutorService.scheduleWithFixedDelay(
                () -> {
                    val i = RANDOM.nextInt(0, 100);
                    TableMetrics tableMetrics = new TableMetrics("table1", ImmutableMap.of("AA", "aa", "k1", "v" + i, "k2", "v2" + i), i);
                    tableMetrics.bindTo(tableMeterRegistry);
                }, 500, 500, TimeUnit.MILLISECONDS);
    }

}
