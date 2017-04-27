package com.example.controller;

import com.example.model.Event;
import com.example.modelDto.PersonDto;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
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
    @PostMapping
    public ResponseEntity<PersonDto> addPersonToEvent(@Valid @RequestBody PersonDto personDto, @RequestParam String eventTitle) throws ConstraintViolationException, EntityExistsException, EntityNotFoundException{
        return new ResponseEntity<>(personService.addPersonToEvent(personDto, eventTitle), HttpStatus.CREATED);
    }


    @DeleteMapping("/deletePerson/{id_person}")
    public ResponseEntity<String> deletePersonById(@PathVariable Long id_person) throws EntityNotFoundException {
        return new ResponseEntity<>(personService.deletePersonById(id_person), HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping("/findPeopleByIdEvent")
    public List<String> findPeopleByIdEvent(@RequestParam Long id) throws EntityNotFoundException, NoResultException {
        return personService.findPeopleByIdEvent(id);
    }


    @PutMapping("/updatePerson/{id_person}")
    public ResponseEntity<String> updatePerson(@Valid @RequestBody PersonDto personDto, @PathVariable Long id_person) throws ConstraintViolationException, EntityNotFoundException {
        String responseMessage = personService.updatePerson(personDto, id_person);
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }
}
