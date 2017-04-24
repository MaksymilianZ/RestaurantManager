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

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService=eventService;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        return new ResponseEntity(eventService.createEvent(eventDto), HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteEventByTitle")
    public ResponseEntity<EventDto> deleteEventByTitle(@RequestParam String eventTitle){
         return new ResponseEntity<EventDto>(eventService.deleteEventByTitle(eventTitle), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEventById/{id_event}")
    public ResponseEntity<EventDto> deleteEventById(@PathVariable Long id_event) {
        return new ResponseEntity<EventDto>(eventService.deleteEventById(id_event), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/checkEventDate")
        public String checkEventDate (@RequestParam Integer year, @RequestParam Integer month, @RequestParam Integer day) {
         return eventService.checkEventDate(year,month,day);
    }

    @ResponseBody
    @PostMapping ("/allEvents")
    public ResponseEntity<List<EventDto>> getAlleventsByYear(@RequestParam Integer year){
        return new ResponseEntity<List<EventDto>>(eventService.getAllYearEvents(year), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/findEvent")
    public ResponseEntity<EventDto> findEventByTitle(@RequestParam String eventTitle) {
        return new ResponseEntity<EventDto>(eventService.findEventByTitle(eventTitle), HttpStatus.OK);
    }

    @PostMapping(value = "/updateEvent/{id_event}")
    public ResponseEntity<String> updateEvent(@RequestBody EventDto eventDto, @PathVariable Long id_event) {
        String responseMessage = eventService.updateEvent(eventDto,id_event);
        return new ResponseEntity<String>(responseMessage, HttpStatus.CREATED);
    }




}

