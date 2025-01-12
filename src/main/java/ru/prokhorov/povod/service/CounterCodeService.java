package ru.prokhorov.povod.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.prokhorov.povod.dto.CountryInfo;
import ru.prokhorov.povod.dto.Holiday;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * CounterCodeService.
 *
 * @author Evgeniy_Prokhorov
 */

@Service
@RequiredArgsConstructor
public class CounterCodeService {

    private final RestTemplate restCountriesRestClient;

    public String getCounterCode(String counterName) {

        ParameterizedTypeReference<CountryInfo> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<CountryInfo> response = restCountriesRestClient.exchange("/v3.1/translation/{translation}",
                HttpMethod.GET,
                new HttpEntity<>(new CountryInfo()),
                responseType,
                counterName);

        if (response.getStatusCode().is2xxSuccessful()) {

            return Objects.requireNonNull(response.getBody())
                    .getTranslations().getRus().getCommon();
        }
        return Strings.EMPTY;
    }
}
