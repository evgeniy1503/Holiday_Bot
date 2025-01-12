package ru.prokhorov.povod.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

/**
 * RestProperties.
 *
 * @author Evgeniy_Prokhorov
 */
@Getter
@Setter
public class RestProperties {
    private String url;
    private Duration connectTimeout;
    private Duration readTimeout;
}
