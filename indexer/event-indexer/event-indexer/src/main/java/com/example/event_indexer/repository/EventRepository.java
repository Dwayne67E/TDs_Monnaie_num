package com.example.event_indexer.repository;

import com.example.event_indexer.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    // Méthodes personnalisées si besoin
}
