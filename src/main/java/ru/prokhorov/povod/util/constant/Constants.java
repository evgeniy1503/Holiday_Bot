package ru.prokhorov.povod.util.constant;

import lombok.experimental.UtilityClass;

/**
 * Постоянные.
 *
 * @author Evgeniy_Prokhorov
 */
@UtilityClass
public class Constants {

    @UtilityClass
    public class URL {
        public String HOLIDAYS_URL = "/api/v3/publicholidays/{Year}/{CountryCode}";
        public String COUNTER_URL = "/v3.1/translation/{translation}";

    }
}
