package com.example.repository;

import com.example.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Maksymilian on 2017-04-18.
 */

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByYearOrderByMonthAsc(Integer year);

    void deleteByTitle(String title);

    Event findByTitle(String title);

}
