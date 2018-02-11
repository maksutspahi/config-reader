package com.ty.config;

import com.ty.model.Configuration;
import com.ty.repository.ConfigurationRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty("configuration.reader.mongo.enabled")
@Slf4j
public final class MongoConfigurationReader implements ConfigurationReader {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Value("${application.name}")
    @Setter
    private String applicationName;

    private Map<String, Object> values = new HashMap<>();

    @PostConstruct
    public void init() {
        loadValues();
    }

    @Scheduled(fixedRateString = "${configuration.reader.mongo.refreshTimerIntervalInMs:10000}")
    protected void loadValues() {
        try {
            List<Configuration> result = configurationRepository.findByApplicationNameAndIsActiveTrue(applicationName);
            values = result
                    .stream()
                    .collect(Collectors.toMap(e -> e.getName(),
                            e -> extractValue(e)));
        } catch (Exception e) {
            log.error("Error loading configuration", e);
        }

    }

    private Object extractValue(Configuration configuration) {
        return configuration.getType().getValue(configuration.getValue());
    }

    @Override
    public <T> T getValue(String key) {
        Object result = values.get(key);
        return (T) result;
    }


}
