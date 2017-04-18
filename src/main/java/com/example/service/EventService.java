package com.example.service;

import com.example.model.Event;
import com.example.model.Person;
import com.example.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Maksymilian on 2017-04-18.
 */

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository=eventRepository;
    }

    private List<Person> personToAdd;

    @Transactional
    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent(String title) {
        List<Event> events = eventRepository.findAll();
        for (Event event: events) {
            if (event.getTitle().equals(title)){
                eventRepository.delete(event);
            }
        }
    }

    @Transactional
    public void addPerson(Person person) {
        personToAdd.add(person);
    }

}
