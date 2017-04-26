package com.example.repository;

import com.example.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Maksymilian on 2017-04-22.
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByPeselIn(String pesel);
}
