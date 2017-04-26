package com.example.service;

import com.example.mappers.PersonMapper;
import com.example.model.Event;
import com.example.model.Person;
import com.example.modelDto.PersonDto;
import com.example.repository.EventRepository;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksymilian on 2017-04-22.
 */

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final EventRepository eventRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, EventRepository eventRepository) {
        this.personRepository=personRepository;
        this.eventRepository=eventRepository;
    }

    public PersonDto addPersonToEvent (PersonDto personDto, String eventTitle)  throws EntityExistsException, EntityNotFoundException {
        Person person = PersonMapper.INSTANCE.personDtoToPerson(personDto);
        Person checkingPerson = personRepository.findByPeselIn(person.getPesel());
        if(checkingPerson!=null) {
            throw new EntityExistsException("Person already exist");
        }
            Event event = eventRepository.findByTitle(eventTitle);
        if(event==null) {
            throw new EntityNotFoundException("No such event. Cannot add person.");
        }
            person.setEvent(event);
            personRepository.save(person);
        PersonDto returnPersonDto = PersonMapper.INSTANCE.personToPersonDto(person);
            return returnPersonDto;
    }


    public PersonDto deletePersonById(Long id) throws EntityNotFoundException {
        Person person = personRepository.findOne(id);
        if (person==null) {
            throw new EntityNotFoundException("No such person. Check id");
        }
        personRepository.delete(id);
        PersonDto returnPersonDto = PersonMapper.INSTANCE.personToPersonDto(person);
        return returnPersonDto;
    }


    public List<String> findPeopleByIdEvent(Long id) throws EntityNotFoundException, NoResultException{
        List<Person> people = personRepository.findAll();
        Event event = eventRepository.findOne(id);
        if(event==null) {
            throw new EntityNotFoundException("No such person. Check id");
        }
        List<String> peopleFound = new ArrayList<>();
        for (Person person : people) {
            if(id.equals(person.getEvent().getId())) {
                peopleFound.add(person.getFirstName() + " " + person.getLastName() + " - " + person.getDetails() + ",");
            }
        }
        if (peopleFound.size()==0){
            throw new NoResultException("No people belongs to event");
        }
        return  peopleFound;
    }


    public String updatePerson(PersonDto personDto, Long id_person) throws EntityNotFoundException {
        Person foundPerson = personRepository.findOne(id_person);
        if (foundPerson==null) {
            throw new EntityNotFoundException("Update failed. No such person. Check id");
        }
            Event eventFromFoundPerson  = foundPerson.getEvent();
            personRepository.delete(id_person);
            Person person = PersonMapper.INSTANCE.personDtoToPerson(personDto);
            person.setEvent(eventFromFoundPerson);
            personRepository.save(person);
            return "Person " + person.getFirstName() + " " + person.getLastName() + " updated";
        }
    }
