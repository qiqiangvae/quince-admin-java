package org.example.quince.admin.metrics.registry;

import io.micrometer.core.instrument.config.validate.Validated;
import io.micrometer.core.instrument.step.StepRegistryConfig;

import static io.micrometer.core.instrument.config.MeterRegistryConfigValidator.checkAll;
import static io.micrometer.core.instrument.config.MeterRegistryConfigValidator.checkRequired;
import static io.micrometer.core.instrument.config.validate.PropertyValidator.getUrlString;

/**
 * @author quince
 */
public interface TableRegistryConfig extends StepRegistryConfig {

    @Override
    default String prefix() {
        return "table";
    }

    default String url() {
        return getUrlString(this, "url").orElse(null);
    }

    @Override
    default Validated<?> validate() {
        return checkAll(this, checkRequired("url", TableRegistryConfig::url));
    }
}
