package ru.prokhorov.povod.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.prokhorov.povod.dto.Holiday;

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

    public List<Holiday> getHolidays(String countryCode, LocalDate date) {

        ParameterizedTypeReference<List<Holiday>> responseType = new ParameterizedTypeReference<>() {
        };


        ResponseEntity<List<Holiday>> response = nagerDateRestClient.exchange("/api/v3/PublicHolidays/{Year}/{CountryCode}",
                HttpMethod.GET,
                new HttpEntity<>(new Holiday()),
                responseType,
                date.getYear(),
                countryCode);

        if (response.getStatusCode().is2xxSuccessful()) {

            return Objects.requireNonNull(response.getBody()).stream()
                    .filter(holiday -> holiday.getDate().equals(date))
                    .toList();
        }
        return List.of();
    }
}
