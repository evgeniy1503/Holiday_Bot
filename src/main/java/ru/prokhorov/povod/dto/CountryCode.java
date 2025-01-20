package ru.prokhorov.povod.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * CountryInfo.
 *
 * @author Evgeniy_Prokhorov
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryCode {

    private String cca2;
}
