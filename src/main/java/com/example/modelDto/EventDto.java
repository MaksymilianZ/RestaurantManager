package com.example.modelDto;

import com.example.model.Person;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksymilian on 2017-04-19.
 */
public class EventDto {

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Year cannot be null")
    private Integer year;

    @NotNull(message = "Month cannot be null")
    private Integer month;

    @NotNull(message = "Day cannot be null")
    private Integer day;
    private String description;
    private List<Person> people = new ArrayList<>();

    public EventDto(){};

    public EventDto(String title, Integer year, Integer month, Integer day, String description, List<Person> people) {
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
        this.people = people;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
