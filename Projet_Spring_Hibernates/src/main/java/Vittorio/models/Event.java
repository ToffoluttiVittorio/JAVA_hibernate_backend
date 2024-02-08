package Vittorio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String theme;
    private String date_debut;
    private int duree;
    private int nb_participant_max;
    private String description;
    private String organisateur;
    private String type_event;

    @OneToMany(mappedBy = "event", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();


    public Event() {

    }

    public Event(String titre, String theme, String date_debut, int duree, int nb_participant_max,
                 String description, String organisateur, String type_event, List<Participant> participants) {
        this.titre = titre;
        this.theme = theme;
        this.date_debut = date_debut;
        this.duree = duree;
        this.nb_participant_max = nb_participant_max;
        this.description = description;
        this.organisateur = organisateur;
        this.type_event = type_event;
        this.participants = participants;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getNb_participant_max() {
        return nb_participant_max;
    }

    public void setNb_participant_max(int nb_participant_max) {
        this.nb_participant_max = nb_participant_max;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }


    public List<Participant> getParticipants() {
        return participants;
    }

    // Méthode d'ajout d'un participant à la liste
    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.setEvent(this);
    }

    // Méthode de suppression d'un participant de la liste
    public void removeParticipant(Participant participant) {
        participants.remove(participant);
        participant.setEvent(null);
    }


}
