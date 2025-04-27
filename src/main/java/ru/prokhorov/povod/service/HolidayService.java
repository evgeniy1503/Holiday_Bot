package ru.prokhorov.povod.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.prokhorov.povod.aspect.logging.annotation.Logging;
import ru.prokhorov.povod.dto.PublicHoliday;
import ru.prokhorov.povod.util.constant.Constants;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для получения праздников.
 *
 * @author Evgeniy_Prokhorov
 */
@Service
@RequiredArgsConstructor
public class HolidayService {

    private final RestTemplate nagerDateRestClient;

    @Logging
    public List<PublicHoliday> getHolidays(final String countryCode,
                                           final LocalDate date) throws JsonProcessingException {

        List<PublicHoliday> result = null;
        final ParameterizedTypeReference<List<PublicHoliday>> responseType = new ParameterizedTypeReference<>() {
        };
        final ResponseEntity<List<PublicHoliday>> response = nagerDateRestClient.exchange(Constants.URL.HOLIDAYS_URL,
                HttpMethod.GET,
                new HttpEntity<>(""),
                responseType,
                date.getYear(),
                countryCode);
        if (response.getStatusCode().is2xxSuccessful()) {
            result =  response.getBody();
        }
        return result;
    }
}
