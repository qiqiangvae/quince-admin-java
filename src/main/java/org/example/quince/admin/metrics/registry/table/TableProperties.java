package org.example.quince.admin.metrics.registry.table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.actuate.autoconfigure.metrics.export.properties.StepRegistryProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author quince
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "management.metrics.export.table")
public class TableProperties extends StepRegistryProperties {
    private String url;
}
