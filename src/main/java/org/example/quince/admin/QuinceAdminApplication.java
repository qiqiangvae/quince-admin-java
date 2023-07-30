package org.example.quince.admin;

import org.example.quince.admin.metrics.registry.TableMetricsExportAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author quince
 */
@SpringBootApplication
@ImportAutoConfiguration(TableMetricsExportAutoConfiguration.class)
public class QuinceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuinceAdminApplication.class, args);
    }

}
