
package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllDoctors_ShouldReturnListOfDoctors() throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("John", "Doe", 35, "john.doe@example.com"));
        doctors.add(new Doctor("Jane", "Smith", 42, "jane.smith@example.com"));

        Mockito.when(doctorRepository.findAll()).thenReturn(doctors);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))) // Verificar que se devuelvan 2 doctores
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("John"))) // Verificar el nombre del primer doctor
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.is("Smith"))); // Verificar el apellido del segundo doctor

        Mockito.verify(doctorRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getDoctorById_ExistingId_ShouldReturnDoctor() throws Exception {
        Doctor doctor = new Doctor("John", "Doe", 35, "john.doe@example.com");
        doctor.setId(1L);

        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctors/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1))) // Verificar el ID del doctor
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("John"))); // Verificar el nombre del doctor

        Mockito.verify(doctorRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void getDoctorById_NonExistingId_ShouldReturnNotFound() throws Exception {
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctors/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(doctorRepository, Mockito.times(1)).findById(1L);
    }
}



@WebMvcTest(PatientController.class)
class PatientControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllPatients_ShouldReturnListOfPatients() throws Exception {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("John", "Doe", 35, "john.doe@example.com"));
        patients.add(new Patient("Jane", "Smith", 42, "jane.smith@example.com"));

        Mockito.when(patientRepository.findAll()).thenReturn(patients);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))) // Verificar que se devuelvan 2 pacientes
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("John"))) // Verificar el nombre del primer paciente
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.is("Smith"))); // Verificar el apellido del segundo paciente

        Mockito.verify(patientRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getPatientById_ExistingId_ShouldReturnPatient() throws Exception {
        Patient patient = new Patient("John", "Doe", 35, "john.doe@example.com");
        patient.setId(1L);

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1))) // Verificar el ID del paciente
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("John"))); // Verificar el nombre del paciente

        Mockito.verify(patientRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void getPatientById_NonExistingId_ShouldReturnNotFound() throws Exception {
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(patientRepository, Mockito.times(1)).findById(1L);
    }
}


@WebMvcTest(RoomController.class)
class RoomControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllRooms_ShouldReturnListOfRooms() throws Exception {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Room 1"));
        rooms.add(new Room("Room 2"));

        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))) // Verificar que se devuelvan 2 salas
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roomName", Matchers.is("Room 1"))) // Verificar el nombre de la primera sala
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roomName", Matchers.is("Room 2"))); // Verificar el nombre de la segunda sala

        Mockito.verify(roomRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getRoomByRoomName_ExistingRoomName_ShouldReturnRoom() throws Exception {
        Room room = new Room("Room 1");

        Mockito.when(roomRepository.findByRoomName("Room 1")).thenReturn(Optional.of(room));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/{roomName}", "Room 1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roomName", Matchers.is("Room 1"))); // Verificar el nombre de la sala

        Mockito.verify(roomRepository, Mockito.times(1)).findByRoomName("Room 1");
    }

    @Test
    void getRoomByRoomName_NonExistingRoomName_ShouldReturnNotFound() throws Exception {
        Mockito.when(roomRepository.findByRoomName("Room 1")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rooms/{roomName}", "Room 1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(roomRepository, Mockito.times(1)).findByRoomName("Room 1");
    }
}

