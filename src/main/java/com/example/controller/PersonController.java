package com.example.controller;

import com.example.model.Event;
import com.example.modelDto.PersonDto;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maksymilian on 2017-04-22.
 */

@RestController
@RequestMapping("/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping("/addPersonToEvent")
    @ResponseBody
    public String addPersonToEvent(@RequestBody PersonDto personDto, @RequestParam String eventTitle) {
        return personService.addPersonToEventServiceMethod(personDto, eventTitle);
    }


    @DeleteMapping("/deletePerson/{id_person}")
    public void deletePersonById(@PathVariable Long id_person) {
        personService.deletePersonByIdServiceMethod(id_person);
    }


    @ResponseBody
    @PostMapping("/findPeopleTakingPartInEvent")
    public List<String> findPeopleByIdEvent(@RequestParam Long id) {
        return personService.findPeopleByIdEventServiceMethod(id);
    }


    @PostMapping("/updatePerson/{id_person}")
    public ResponseEntity<String> updatePerson(@RequestBody PersonDto personDto, @PathVariable Long id_person) {
        String responseMessage = personService.updatePersonServiceMethod(personDto, id_person);
        return new ResponseEntity<String>(responseMessage, HttpStatus.CREATED);
    }
}
