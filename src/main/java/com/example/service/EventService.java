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

/**
 * Created by Maksymilian on 2017-04-18.
 */

@Service
public class EventService {

    private EventRepository eventRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository=eventRepository;
    }

    @Transactional
    public void addEvent(Event event) {
        entityManager.persist(event);
    }

    @Transactional
    public void addPersonToEvent(Person person, String eventTitle) {
        for (Event event : eventRepository.findAll()) {
            if(event.getTitle().equals(eventTitle)){
                event.getPeople().add(person);
            }
        }
    }

}
