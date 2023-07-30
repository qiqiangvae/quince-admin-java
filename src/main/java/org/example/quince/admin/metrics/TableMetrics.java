package org.example.quince.admin.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author quince
 */
@RequiredArgsConstructor
public class TableMetrics implements MeterBinder {
    private final String tableName;
    private final Map<String, String> rowData;
    private final Number value;

    @Override
    public void bindTo(MeterRegistry registry) {
        List<Tag> tags = new ArrayList<>(rowData.size());
        rowData.forEach((s, s2) -> tags.add(new Tag() {
            @Override
            public String getKey() {
                return s;
            }

            @Override
            public String getValue() {
                return s2;
            }
        }));
        Gauge.builder(tableName, () -> value).tags(tags)
                .register(registry);
    }
}
