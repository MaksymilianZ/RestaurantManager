package com.example.controller;

import com.example.model.Event;
import com.example.model.Person;
import com.example.modelDto.EventDto;
import com.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maksymilian on 2017-04-18.
 */

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService=eventService;
    }

    @PostMapping(value = "/addEvent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addEvent(@RequestBody EventDto eventDto) {
        eventService.addEvent(eventDto);
    }

    @PostMapping(path = "/deleteEventByTitle")
        public void deleteByTitle(@RequestParam String titleName){
        eventService.deleteEventByTitle(titleName);
    }

    @DeleteMapping("/delete/{id_event}")
    public void deleteById(@PathVariable Long id_event) {
        eventService.deleteEventById(id_event);
    }

    @ResponseBody
    @PostMapping(value = "/checkEventDate")
        public String checkEventDate (@RequestParam Integer year, @RequestParam Integer month, @RequestParam Integer day) {
         return eventService.checkDate(year,month,day);
    }

    @ResponseBody
    @PostMapping (value = "/allEvents")
    public List<EventDto> getAllEvents(@RequestParam Integer year){
        return eventService.allEvents(year);
    }



}

