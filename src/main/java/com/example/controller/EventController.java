package com.example.controller;


import com.example.model.Event;
import com.example.modelDto.EventDto;
import com.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/createEvent")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEvent(@RequestBody EventDto eventDto) {
        eventService.createEventServiceMethod(eventDto);
    }

    @DeleteMapping("/deleteEventByTitle")
        public void deleteEventByTitle(@RequestParam String eventTitle){
        eventService.deleteEventByTitleServiceMethod(eventTitle);
    }

    @DeleteMapping("/deleteEventById/{id_event}")
    public void deleteEventById(@PathVariable Long id_event) {
        eventService.deleteEventByIdServiceMethod(id_event);
    }

    @ResponseBody
    @PostMapping("/checkEventDate")
        public String checkEventDate (@RequestParam Integer year, @RequestParam Integer month, @RequestParam Integer day) {
         return eventService.checkEventDateServiceMethod(year,month,day);
    }

    @ResponseBody
    @PostMapping ("/allEvents")
    public List<EventDto> getAllYearEvents(@RequestParam Integer year){
        List<EventDto> listToReturn = eventService.getAllYearEventsServiceMethod(year);
        return listToReturn;
    }

    @ResponseBody
    @PostMapping("/findEvent")
    public Event findEventByTitle(@RequestParam String eventTitle) {
        return eventService.findEventByTitleServiceMethod(eventTitle);
    }

    @PostMapping(value = "/updateEvent/{id_event}")
    public ResponseEntity<String> updateEvent(@RequestBody EventDto eventDto, @PathVariable Long id_event) {
        String responseMessage = eventService.updateEventServiceMethod(eventDto,id_event);
        return new ResponseEntity<String>(responseMessage, HttpStatus.CREATED);
    }




}

