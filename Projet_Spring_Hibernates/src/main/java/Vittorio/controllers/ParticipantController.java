package Vittorio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Vittorio.models.Event;
import Vittorio.models.Participant;
import Vittorio.services.EventService;
import Vittorio.services.ParticipantService;

import java.util.List;

@Controller
public class ParticipantController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/myevents")
    public String listArticles(Model model) {
        List<Event> events = eventService.findAll();
        List<Participant> participants = participantService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("participants", participants);
        return "events";
    }

    @GetMapping("/addevent")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("event", new Event());
        return "addevent";
    }
}
