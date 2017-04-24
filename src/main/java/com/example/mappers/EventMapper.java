package com.example.mappers;

import com.example.model.Event;
import com.example.modelDto.EventDto;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

/**
 * Created by Maksymilian on 2017-04-19.
 */
@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    Event eventDtoToEvent (EventDto eventDto);
    EventDto eventToEventDto(Event event);
}
