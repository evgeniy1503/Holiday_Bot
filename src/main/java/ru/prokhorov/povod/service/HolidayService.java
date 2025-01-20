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
import ru.prokhorov.povod.dto.PublicHoliday;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * HolidayService.
 *
 * @author Evgeniy_Prokhorov
 */
@Service
@RequiredArgsConstructor
public class HolidayService {

    private final RestTemplate nagerDateRestClient;
    private final ObjectMapper objectMapper;

    public List<PublicHoliday> getHolidays(String countryCode, LocalDate date) throws JsonProcessingException {
        ParameterizedTypeReference<List<PublicHoliday>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<PublicHoliday>> response = nagerDateRestClient.exchange("/api/v3/publicholidays/{Year}/{CountryCode}",
                HttpMethod.GET,
                new HttpEntity<>(""),
                responseType,
                date.getYear(),
                countryCode);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return null;
    }
}
