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

    private final ObjectMapper objectMapper;

    public String getCounterCode(String counterName) throws JsonProcessingException {

        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<>() {
        };

        ResponseEntity<String> response = restCountriesRestClient.exchange("/v3.1/translation/{translation}",
                HttpMethod.GET,
                new HttpEntity<>(""),
                responseType,
                counterName);

        if (response.getStatusCode().is2xxSuccessful()) {
            String json = Objects.requireNonNull(response.getBody());
            return objectMapper.readTree(json).get(0).get("tld").get(0).asText().replace(".","");
        }
        return Strings.EMPTY;
    }
}
