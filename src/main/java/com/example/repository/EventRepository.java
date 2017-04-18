package com.example.repository;

import com.example.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Maksymilian on 2017-04-18.
 */

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
