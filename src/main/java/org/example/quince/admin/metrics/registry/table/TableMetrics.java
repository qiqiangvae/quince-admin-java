package org.example.quince.admin.metrics.registry.table;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.NonNull;
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
    public void bindTo(@NonNull MeterRegistry registry) {
        List<Tag> tags = new ArrayList<>(rowData.size());
        rowData.forEach((s, s2) -> tags.add(new ImmutableTag(s, s2)));
        Gauge.builder(tableName, () -> value).tags(tags)
                .register(registry);
    }
}
