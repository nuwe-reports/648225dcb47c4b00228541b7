package com.example.demo.dto;

import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.entities.Room;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class AppointmentDTO {

    private Patient patient;
    private Doctor doctor;
    private Room room;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime startsAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime finishesAt;


    public AppointmentDTO(Patient patient, Doctor doctor, Room room, LocalDateTime startsAt, LocalDateTime finishesAt) {
        setPatient(patient);
        setDoctor(doctor);
        setRoom(room);
        setStartsAt(startsAt);
        setFinishesAt(finishesAt);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public void setFinishesAt(LocalDateTime finishesAt) {
        this.finishesAt = finishesAt;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public LocalDateTime getFinishesAt() {
        return finishesAt;
    }
}
