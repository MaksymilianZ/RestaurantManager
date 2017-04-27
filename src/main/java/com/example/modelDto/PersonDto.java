package com.example.modelDto;

import com.example.model.Event;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by Maksymilian on 2017-04-19.
 */

public class PersonDto {

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @Size(min = 11, max = 11)
    private String pesel;
    private String details;
    private Event event;

    public PersonDto(){};

    public PersonDto(String firstName, String lastName, String pesel, String details, Event event) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.details = details;
        this.event = event;
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

    @Override
    public String toString() {
        return "PersonDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", details='" + details + '\'' +
                ", event=" + event.getTitle() +
                '}';
    }
}


