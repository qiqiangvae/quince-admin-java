package org.example.quince.admin.config;

import io.micrometer.influx.InfluxMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @author quince
 */
@Slf4j
@Component
public class MeterConfig implements MeterRegistryCustomizer<InfluxMeterRegistry> {
    @Override
    public void customize(InfluxMeterRegistry registry) {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            if (log.isDebugEnabled()) {
                log.debug("设置metrics实例id为ip:" + hostAddress);
            }
            registry.config().commonTags("instance-id", hostAddress);
        } catch (UnknownHostException e) {
            String uuid = UUID.randomUUID().toString();
            registry.config().commonTags("instance-id", uuid);
            log.error("获取实例ip失败，设置实例id为uuid:" + uuid, e);
        }
    }
}
