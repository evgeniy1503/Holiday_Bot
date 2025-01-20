package ru.prokhorov.povod.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * PublicHoliday.
 *
 * @author Evgeniy_Prokhorov
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicHoliday {
    private String date;
    private String localName;
    private String name;
    private String countryCode ;
    private Boolean fixed;
    private Boolean global;
    private String[] counties;
    private String[] types;
}
