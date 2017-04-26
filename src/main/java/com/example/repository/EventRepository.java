package com.example.repository;

import com.example.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Maksymilian on 2017-04-18.
 */

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByYearOrderByMonthAsc(Integer year);

    void deleteByTitle(String title) throws EntityNotFoundException;

    Event findByTitle(String title) throws EntityNotFoundException;

}
