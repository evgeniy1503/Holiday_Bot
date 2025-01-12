package ru.prokhorov.povod.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Holiday.
 *
 * @author Evgeniy_Prokhorov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Holiday {
    private LocalDate date;
    private String name;

    @JsonProperty("date")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}
