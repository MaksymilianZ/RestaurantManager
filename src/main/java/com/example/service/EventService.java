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

    @Transactional
    public void addEvent(EventDto eventDto) {
        Event event = EventMapper.INSTANCE.EventDtoToEvent( eventDto );
        eventRepository.save(event);
    }

    @Transactional
    public void deleteEventByTitle(String title) {
        List<Event> events = eventRepository.findAll();
        for (Event event: events) {
            if (event.getTitle().equals(title)){
                eventRepository.delete(event);
            }
        }
    }

    @Transactional
    public void deleteEventById(Long id_event) {
        eventRepository.delete(id_event);
    }

    @Transactional
    public String checkDate(Integer year, Integer month, Integer day) {
        List<Event> actuallEvents = eventRepository.findAll();
        String dateCheckMessage = "notReserved";
        for(Event event : actuallEvents) {
            if(year.equals(event.getYear())&&month.equals(event.getMonth())&&day.equals(event.getDay())){
                 dateCheckMessage = "reserved";
            }
        }
        return dateCheckMessage;
    }

    @Transactional
    public List<EventDto> allEvents(Integer year) {
        List<Event> allEvents= eventRepository.findByYearOrderByMonthAsc(year);
        List<EventDto> allEventsDto = new ArrayList<>();
        for (Event event : allEvents) {
            EventDto eventDto = EventMapper.INSTANCE.EventToEventDto(event);
            allEventsDto.add(eventDto);
        }
        return  allEventsDto;
    }


}
