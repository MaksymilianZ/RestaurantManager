package com.example.service;

import com.example.mappers.PersonMapper;
import com.example.model.Event;
import com.example.model.Person;
import com.example.modelDto.PersonDto;
import com.example.repository.EventRepository;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PersonDto addPersonToEvent (PersonDto personDto, String eventTitle){
        Person person = PersonMapper.INSTANCE.personDtoToPerson(personDto);
            Event event = eventRepository.findByTitle(eventTitle);
            person.setEvent(event);
            personRepository.save(person);
        PersonDto returnPersonDto = PersonMapper.INSTANCE.personToPersonDto(person);
            return returnPersonDto;
    }


    public PersonDto deletePersonById(Long id) {
        Person person = personRepository.findOne(id);
        personRepository.delete(id);
        PersonDto returnPersonDto = PersonMapper.INSTANCE.personToPersonDto(person);
        return returnPersonDto;
    }


    public List<String> findPeopleByIdEvent(Long id){
        List<Person> people = personRepository.findAll();
        List<String> peopleFind = new ArrayList<>();
        for (Person person : people) {
            if(id.equals(person.getEvent().getId())) {
                peopleFind.add(person.getFirstName() + " " + person.getLastName() + " - " + person.getDetails() + ",");
            }
        }
        return  peopleFind;
    }


    public String updatePerson(PersonDto personDto, Long id_person) {
        Person foundPerson = personRepository.findOne(id_person);
        if (foundPerson!=null) {
            Event eventFromFoundPerson  = foundPerson.getEvent();
            personRepository.delete(id_person);
            Person person = PersonMapper.INSTANCE.personDtoToPerson(personDto);
            person.setEvent(eventFromFoundPerson);
            personRepository.save(person);
            return "Person " + person.getFirstName() + " " + person.getLastName() + " updated";
        } else {
            return "Person not found";
        }
    }
}
