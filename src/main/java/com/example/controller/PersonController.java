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

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @ResponseBody
    @PostMapping("/addPersonToEvent")
    public ResponseEntity<PersonDto> addPersonToEvent(@RequestBody PersonDto personDto, @RequestParam String eventTitle) {
        return new ResponseEntity<PersonDto>(personService.addPersonToEvent(personDto, eventTitle), HttpStatus.CREATED);
    }


    @DeleteMapping("/deletePerson/{id_person}")
    public ResponseEntity<PersonDto> deletePersonById(@PathVariable Long id_person) {
        return new ResponseEntity<PersonDto>(personService.deletePersonById(id_person), HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping("/findPeopleByIdEvent")
    public List<String> findPeopleByIdEvent(@RequestParam Long id) {
        return personService.findPeopleByIdEvent(id);
    }


    @PostMapping("/updatePerson/{id_person}")
    public ResponseEntity<String> updatePerson(@RequestBody PersonDto personDto, @PathVariable Long id_person) {
        String responseMessage = personService.updatePerson(personDto, id_person);
        return new ResponseEntity<String>(responseMessage, HttpStatus.CREATED);
    }
}
