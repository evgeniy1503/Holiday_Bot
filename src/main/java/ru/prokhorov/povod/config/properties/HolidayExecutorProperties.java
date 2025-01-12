package ru.prokhorov.povod.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * HolidayExecutorProperties.
 *
 * @author Evgeniy_Prokhorov
 */

@ConfigurationProperties(prefix = "integration.holiday-services")
@RequiredArgsConstructor
@Getter
public class HolidayExecutorProperties {

    private final RestProperties nagerDate;

    private final RestProperties restCountries;
}
