package com.example.mappers;

import com.example.model.Person;
import com.example.modelDto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by Maksymilian on 2017-04-22.
 */

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    Person personDtoToPerson (PersonDto personDto);
    PersonDto personToPersonDto (Person person);

}
