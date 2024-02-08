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
import java.util.Objects;
import java.util.Optional;

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

    @PostMapping("/addevent")
    public String addAuthor(@ModelAttribute Event event) {
        eventService.save(event);
        return "redirect:/myevents";
    }

    @GetMapping("/addparticipant")
    public String showAddParticipantForm(Model model) {
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("participant", new Participant());
        return "addParticipant";
    }

    @PostMapping("/addparticipant")
    public String addParticipant(@ModelAttribute Participant participant, Model model) {
        Long eventId = participant.getEvent().getId();

        if (participantService.isEventFull(eventId)) {
            // Ajoutez un message d'erreur à afficher sur la page
            model.addAttribute("errorMessage", "Nombre maximal de participants atteint pour cet événement.");
            // Rechargez la liste des événements pour l'afficher à nouveau
            List<Event> events = eventService.findAll();
            model.addAttribute("events", events);
            return "addParticipant";
        }

        participantService.save(participant);
        return "redirect:/myevents";
    }


    @PostMapping("/participants/delete/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        participantService.deleteById(id);
        return "redirect:/myevents";
    }

    @PostMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/myevents";
    }

    @GetMapping("/editparticipant/{id}")
    public String editParticipant(@PathVariable("id") Long id, Model model) {
        Optional<Participant> participant = participantService.findById(id);
        if (participant.isPresent()) {
            model.addAttribute("participant", participant.get());

            return "editparticipant";
        } else {
            // Gérez l'erreur, par exemple en redirigeant vers une page d'erreur
            return "redirect:/myevents";
        }
    }

    @PostMapping("/editparticipant")
    public String updateParticipant(@ModelAttribute Participant participant) {
        participantService.save(participant);
        return "redirect:/myevents";
    }

    @GetMapping("/editevent/{id}")
    public String editEvent(@PathVariable("id") Long id, Model model) {
        Optional<Event> event = eventService.findById(id);
        if (event.isPresent()) {
            model.addAttribute("event", event.get());

            return "editevent";
        } else {
            // Gérez l'erreur, par exemple en redirigeant vers une page d'erreur
            return "redirect:/myevents";
        }
    }

    @PostMapping("/editevent")
    public String updateEvent(@ModelAttribute Event event) {
        // Supprimer les participants orphelins
        event.getParticipants().removeIf(Objects::isNull);

        // Enregistrer l'événement
        eventService.save(event);

        return "redirect:/myevents";
    }


}


