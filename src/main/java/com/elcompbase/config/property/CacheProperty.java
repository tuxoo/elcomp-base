package com.elcompbase.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "cache")
public record CacheProperty(
        Long userMaximumSize,
        Long userExpiredTimeHours
) {
}
