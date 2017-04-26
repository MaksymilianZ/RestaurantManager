package com.example.service;

import com.example.mappers.EventMapper;
import com.example.model.Event;
import com.example.modelDto.EventDto;
import com.example.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksymilian on 2017-04-18.
 */

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public EventDto createEvent(EventDto eventDto) throws EntityExistsException{
        Event event = EventMapper.INSTANCE.eventDtoToEvent( eventDto );
        Event checkingEvent = eventRepository.findByTitle(event.getTitle());
        if (checkingEvent!=null) {
            throw new EntityExistsException("Event already exist.");
        }
        eventRepository.save(event);
        EventDto returnEventDto = EventMapper.INSTANCE.eventToEventDto(event);
        return returnEventDto;
    }


    @Transactional
    public EventDto deleteEventByTitle(String eventTitle) throws EntityNotFoundException{
        Event event = eventRepository.findByTitle(eventTitle);
        if(event==null) {
            throw new EntityNotFoundException("No such event. Check title.");
        }
        eventRepository.deleteByTitle(eventTitle);
        EventDto returnEventDto = EventMapper.INSTANCE.eventToEventDto(event);
        return returnEventDto;
    }


    public EventDto deleteEventById(Long id_event) throws EntityNotFoundException {
        Event event = eventRepository.findOne(id_event);
        if(event==null)
            throw new EntityNotFoundException("No such event. Check id.");
        eventRepository.delete(id_event);
        EventDto returnEventDto = EventMapper.INSTANCE.eventToEventDto(event);
        return returnEventDto;
    }


    public String checkEventDate(Integer year, Integer month, Integer day) {
        List<Event> actualEvents = eventRepository.findAll();
        String dateCheckMessage = "Date not reserved";
        for(Event event : actualEvents) {
            if(year.equals(event.getYear())&&month.equals(event.getMonth())&&day.equals(event.getDay())){
                 dateCheckMessage = "Date reserved";
            }
        }
        return dateCheckMessage;
    }


    public List<EventDto> getAllYearEvents(Integer year) throws NoResultException {
        List<Event> allEvents= eventRepository.findByYearOrderByMonthAsc(year);
        if(allEvents.size()==0) {
            throw new NoResultException("No events im memory.");
        }
        List<EventDto> allEventsDto = new ArrayList<>();
        for (Event event : allEvents) {
            EventDto eventDto = EventMapper.INSTANCE.eventToEventDto(event);
            allEventsDto.add(eventDto);
        }
        return  allEventsDto;
    }


    public EventDto findEventByTitle(String eventTitle) throws EntityNotFoundException{
        Event event = eventRepository.findByTitle(eventTitle);
        if(event==null) {
            throw new EntityNotFoundException("No such event. Check title.");
        }
        EventDto returnEventDto = EventMapper.INSTANCE.eventToEventDto(event);
        return returnEventDto;
    }


    public String updateEvent(EventDto eventDto, Long id_event) throws EntityNotFoundException {
        Event foundEvent = eventRepository.findOne(id_event);
        if(foundEvent==null) {
            throw new EntityNotFoundException("Update failed. No such event. Check id");
        }
            eventRepository.delete(id_event);
            Event event = EventMapper.INSTANCE.eventDtoToEvent(eventDto);
            eventRepository.save(event);
            return "Event " + event.getTitle() +  " updated";
        }
    }
