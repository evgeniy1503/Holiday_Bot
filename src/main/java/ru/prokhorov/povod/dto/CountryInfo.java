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
public class CountryInfo {

    private Translations translations;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Translations {
        private Rus rus;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Rus{
        private String official;
        private String common;
    }
}
