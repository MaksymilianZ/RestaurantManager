package com.example.controller;

import com.example.model.Event;
import com.example.model.Person;
import com.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maksymilian on 2017-04-18.
 */

@Controller
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService=eventService;
    }

    @PostMapping(value = "/addEvent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }

    @PostMapping(value = "/addPerson", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPerson(@RequestBody Person person, @RequestParam(name = "eventTitle") String eventTitle) {
        eventService.addPersonToEvent(person, eventTitle);
    }
}

