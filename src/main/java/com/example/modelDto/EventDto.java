package com.example.modelDto;

import com.example.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksymilian on 2017-04-19.
 */
public class EventDto {

    private Long id = null;
    private String title;
    private Integer year;
    private Integer month;
    private Integer day;
    private String description;
    private List<Person> people = new ArrayList<>();

    public EventDto(){};

    public EventDto(Long id, String title, Integer year, Integer month, Integer day, String description, List<Person> people) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
        this.people = people;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
