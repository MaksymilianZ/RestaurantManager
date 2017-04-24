package com.example.service;

import com.example.mappers.EventMapper;
import com.example.model.Event;
import com.example.model.Person;
import com.example.modelDto.EventDto;
import com.example.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksymilian on 2017-04-18.
 */

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public void createEventServiceMethod(EventDto eventDto) {
        Event event = EventMapper.INSTANCE.EventDtoToEvent( eventDto );
        eventRepository.save(event);
    }


    public void deleteEventByTitleServiceMethod(String eventTitle) {
        eventRepository.deleteByTitle(eventTitle);
    }


    public void deleteEventByIdServiceMethod(Long id_event) {
        eventRepository.delete(id_event);
    }


    public String checkEventDateServiceMethod(Integer year, Integer month, Integer day) {
        List<Event> actualEvents = eventRepository.findAll();
        String dateCheckMessage = "Date not reserved";
        for(Event event : actualEvents) {
            if(year.equals(event.getYear())&&month.equals(event.getMonth())&&day.equals(event.getDay())){
                 dateCheckMessage = "Date reserved";
            }
        }
        return dateCheckMessage;
    }


    public List<EventDto> getAllYearEventsServiceMethod(Integer year) {
        List<Event> allEvents= eventRepository.findByYearOrderByMonthAsc(year);
        List<EventDto> allEventsDto = new ArrayList<>();
        for (Event event : allEvents) {
            EventDto eventDto = EventMapper.INSTANCE.EventToEventDto(event);
            allEventsDto.add(eventDto);
        }
        return  allEventsDto;
    }


    public Event findEventByTitleServiceMethod(String eventTitle) {
        return eventRepository.findByTitle(eventTitle);
    }


    public String updateEventServiceMethod(EventDto eventDto, Long id_event) {
        Event foundEvent = eventRepository.findOne(id_event);
        if(foundEvent!=null) {
            eventRepository.delete(id_event);
            Event event = EventMapper.INSTANCE.EventDtoToEvent(eventDto);
            eventRepository.save(event);
            return "Event has been updated";
        }
            else {
                return  "Event has been not found";
            }
        }
    }
