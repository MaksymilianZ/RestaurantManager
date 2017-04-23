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

    private PersonRepository personRepository;
    private EventRepository eventRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, EventRepository eventRepository) {
        this.personRepository=personRepository;
        this.eventRepository=eventRepository;
    }

    public String addPersonToEventService (PersonDto personDto, String eventTitle){
        Person person = PersonMapper.INSTANCE.PersonDtoToPerson(personDto);
            Event event = eventRepository.findByTitle(eventTitle);
            person.setEvent(event);
            personRepository.save(person);
            return person.toString() + "added";
    }

    public List<String> findPeopleByIdEventService(Long id){
        List<Person> people = personRepository.findAll();
        List<String> peopleFind = new ArrayList<>();
        for (Person person : people) {
            if(id.equals(person.getEvent().getId())) {
                peopleFind.add(person.getFirstName() + " " + person.getLastName() + " - " + person.getDetails() + ",");
            }
        }
        return  peopleFind;
    }

    public void deletePersonById(Long id) {
        personRepository.delete(id);
    }

    public String updatePersonService(PersonDto personDto, Long id_person) {
        Person foundPerson = personRepository.findOne(id_person);
        if (foundPerson!=null) {
            Event eventFromFoundPerson  = foundPerson.getEvent();
            personRepository.delete(id_person);
            Person person = PersonMapper.INSTANCE.PersonDtoToPerson(personDto);
            person.setEvent(eventFromFoundPerson);
            personRepository.save(person);
            return "Person has been updated";
        } else {
            return "Person has been not found";
        }
    }
}
