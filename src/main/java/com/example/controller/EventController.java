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

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

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
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto) throws ConstraintViolationException, EntityExistsException {
        return new ResponseEntity<EventDto>(eventService.createEvent(eventDto), HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteEventByTitle")
    public ResponseEntity<EventDto> deleteEventByTitle(@RequestParam String title) throws EntityNotFoundException{
         return new ResponseEntity<EventDto>(eventService.deleteEventByTitle(title), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEventById/{id_event}")
    public ResponseEntity<EventDto> deleteEventById(@PathVariable Long id_event) throws EntityNotFoundException{
        return new ResponseEntity<EventDto>(eventService.deleteEventById(id_event), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/checkEventDate")
        public String checkEventDate (@RequestParam Integer year, @RequestParam Integer month, @RequestParam Integer day) {
         return eventService.checkEventDate(year,month,day);
    }

    @ResponseBody
    @PostMapping ("/allYearEvents")
    public ResponseEntity<List<EventDto>> getAlleventsByYear(@RequestParam Integer year) throws NoResultException{
        return new ResponseEntity<List<EventDto>>(eventService.getAllYearEvents(year), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/findEvent")
    public ResponseEntity<EventDto> findEventByTitle(@RequestParam String eventTitle) throws EntityNotFoundException{
        return new ResponseEntity<EventDto>(eventService.findEventByTitle(eventTitle), HttpStatus.OK);
    }

    @PostMapping(value = "/updateEvent/{id_event}")
    public ResponseEntity<String> updateEvent(@Valid @RequestBody EventDto eventDto, @PathVariable Long id_event) throws ConstraintViolationException, EntityNotFoundException {
        String responseMessage = eventService.updateEvent(eventDto,id_event);
        return new ResponseEntity<String>(responseMessage, HttpStatus.CREATED);
    }
}

