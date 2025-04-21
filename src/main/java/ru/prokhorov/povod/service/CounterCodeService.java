package ru.prokhorov.povod.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.prokhorov.povod.aspect.logging.annotation.Logging;
import ru.prokhorov.povod.util.constant.Constants;

import java.util.Objects;

/**
 * CounterCodeService.
 *
 * @author Evgeniy_Prokhorov
 */

@Service
@RequiredArgsConstructor
@Logging
public class CounterCodeService {

    private final RestTemplate restCountriesRestClient;

    private final ObjectMapper objectMapper;

    public String getCounterCode(final String counterName) throws JsonProcessingException {

        String result = Strings.EMPTY;
        final ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<>() {
        };

        final ResponseEntity<String> response = restCountriesRestClient.exchange(Constants.URL.COUNTER_URL,
                HttpMethod.GET,
                new HttpEntity<>(""),
                responseType,
                counterName);

        if (response.getStatusCode().is2xxSuccessful()) {
            String json = Objects.requireNonNull(response.getBody());
            result = getCode(json);
        }
        return result;
    }

    private String getCode(final String json) throws JsonProcessingException {
        return objectMapper.readTree(json).get(0).get("tld").get(0).asText().replace(".","");
    }
}
