package com.example.modelDto;

import com.example.model.Event;


/**
 * Created by Maksymilian on 2017-04-19.
 */

public class PersonDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String details;
    private Event event;

    public PersonDto(){};

    public PersonDto(Long id, String firstName, String lastName, String pesel, String details, Event event) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.details = details;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
