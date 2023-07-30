package org.example.quince.admin.metrics.registry.table;

import org.springframework.boot.actuate.autoconfigure.metrics.export.properties.StepRegistryPropertiesConfigAdapter;

/**
 * @author quince
 */
public class TablePropertiesConfigAdapter extends StepRegistryPropertiesConfigAdapter<TableProperties>
        implements TableRegistryConfig {
    public TablePropertiesConfigAdapter(TableProperties properties) {
        super(properties);
    }

    @Override
    public String url() {
        return get(TableProperties::getUrl, TableRegistryConfig.super::url);
    }
}
