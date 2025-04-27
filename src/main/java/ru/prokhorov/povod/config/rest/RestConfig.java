package ru.prokhorov.povod.config.rest;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.prokhorov.povod.config.properties.HolidayExecutorProperties;
import ru.prokhorov.povod.config.properties.RestProperties;

/**
 * Настройка REST.
 *
 * @author Evgeniy_Prokhorov
 */
@Configuration
@EnableConfigurationProperties(
        {HolidayExecutorProperties.class}
)
public class RestConfig {

    @Bean("nagerDateRestClient")
    public RestTemplate nagerDateRestClient(final HolidayExecutorProperties properties,
                                          final RestTemplateErrorResponseHandler errorResponseHandler) {
        final RestProperties nagerDate = properties.getNagerDate();
        return new RestTemplateBuilder()
                .rootUri(nagerDate.getUrl())
                .connectTimeout(nagerDate.getConnectTimeout())
                .readTimeout(nagerDate.getReadTimeout())
                .errorHandler(errorResponseHandler)
                .build();
    }

    @Bean("restCountriesRestClient")
    public RestTemplate restCountriesRestClient(final HolidayExecutorProperties properties,
                                          final RestTemplateErrorResponseHandler errorResponseHandler) {
        final RestProperties restCountries = properties.getRestCountries();
        return new RestTemplateBuilder()
                .rootUri(restCountries.getUrl())
                .connectTimeout(restCountries.getConnectTimeout())
                .readTimeout(restCountries.getReadTimeout())
                .errorHandler(errorResponseHandler)
                .build();
    }
}
