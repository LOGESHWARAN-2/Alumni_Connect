package com.example.AlumniConnect.service;

import com.example.AlumniConnect.dto.EventRequest;
import com.example.AlumniConnect.model.Event;
import com.example.AlumniConnect.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Replaces: Controllers/eventController.js logic
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // getEvents() — same as getEvents in eventController.js
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // getEventById() — same as getEventById in eventController.js
    public Event getEventById(String id) throws Exception {
        return eventRepository.findById(id)
                .orElseThrow(() -> new Exception("Event not found"));
    }

    // createEvent() — same as createEvent in eventController.js
    public Event createEvent(EventRequest request) {
        Event event = new Event();
        event.setDescription(request.getDescription());
        event.setLink(request.getLink());
        event.setDateTime(request.getDateTime());
        event.setCategory(request.getCategory());
        return eventRepository.save(event);
    }

    // updateEvent() — same as updateEvent in eventController.js
    public Event updateEvent(String id, EventRequest request) throws Exception {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new Exception("Event not found"));

        event.setDescription(request.getDescription());
        event.setLink(request.getLink());
        event.setDateTime(request.getDateTime());
        event.setCategory(request.getCategory());

        return eventRepository.save(event);
    }

    // deleteEvent() — same as deleteEvent in eventController.js
    public void deleteEvent(String id) throws Exception {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new Exception("Event not found"));
        eventRepository.delete(event);
    }
}