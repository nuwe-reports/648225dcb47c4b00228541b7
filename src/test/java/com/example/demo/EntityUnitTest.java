package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    private Doctor d1;

    private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

    @BeforeEach
    public void setUp() {
        d1 = new Doctor("John", "Doe", 35, "johndoe@gmail.com");
        p1 = new Patient("Alice", "Smith", 30, "alice@gmail.com");
        r1 = new Room("Room A");
        LocalDateTime startsAt = LocalDateTime.of(2023, Month.JUNE, 8, 10, 0);
        LocalDateTime finishesAt = LocalDateTime.of(2023, Month.JUNE, 8, 11, 0);
        a1 = new Appointment(p1, d1, r1, startsAt, finishesAt);
        a2 = new Appointment(p1, d1, r1, startsAt.plusHours(1), finishesAt.plusHours(1));
        a3 = new Appointment(p1, d1, r1, startsAt.plusHours(2), finishesAt.plusHours(2));
        entityManager.persist(d1);
        entityManager.persist(p1);
        entityManager.persist(r1);
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.persist(a3);
    }

    @AfterEach
    public void tearDown() {
        entityManager.remove(a3);
        entityManager.remove(a2);
        entityManager.remove(a1);
        entityManager.remove(r1);
        entityManager.remove(p1);
        entityManager.remove(d1);
    }

    // Test para verificar la creación de la entidad Doctor
    @Test
    public void testCreateDoctor() {
        Doctor foundDoctor = entityManager.find(Doctor.class, d1.getId());
        assertThat(foundDoctor.getId()).isEqualTo(d1.getId());
        assertThat(foundDoctor.getFirstName()).isEqualTo(d1.getFirstName());
        assertThat(foundDoctor.getLastName()).isEqualTo(d1.getLastName());
        assertThat(foundDoctor.getAge()).isEqualTo(d1.getAge());
        assertThat(foundDoctor.getEmail()).isEqualTo(d1.getEmail());
    }

    // Test para verificar la actualización de la entidad Doctor
    @Test
    public void testUpdateDoctor() {
        d1.setFirstName("Jane");
        d1.setLastName("Doe");
        d1.setAge(36);
        d1.setEmail("janedoe@gmail.com");
        entityManager.persist(d1);

        Doctor foundDoctor = entityManager.find(Doctor.class, d1.getId());

        assertThat(foundDoctor.getId()).isEqualTo(d1.getId());
        assertThat(foundDoctor.getFirstName()).isEqualTo(d1.getFirstName());
        assertThat(foundDoctor.getLastName()).isEqualTo(d1.getLastName());
        assertThat(foundDoctor.getAge()).isEqualTo(d1.getAge());
        assertThat(foundDoctor.getEmail()).isEqualTo(d1.getEmail());
    }

    // Test para verificar la eliminación de la entidad Doctor
    @Test
    public void testDeleteDoctor() {
        entityManager.remove(d1);
        Doctor foundDoctor = entityManager.find(Doctor.class, d1.getId());

        assertThat(foundDoctor).isNull();
    }

    // Test para verificar la creación de la entidad Patient
    @Test
    public void testCreatePatient() {
        Patient foundPatient = entityManager.find(Patient.class, p1.getId());

        assertThat(foundPatient.getId()).isEqualTo(p1.getId());
        assertThat(foundPatient.getFirstName()).isEqualTo(p1.getFirstName());
        assertThat(foundPatient.getLastName()).isEqualTo(p1.getLastName());
        assertThat(foundPatient.getAge()).isEqualTo(p1.getAge());
        assertThat(foundPatient.getEmail()).isEqualTo(p1.getEmail());
    }


    // Test para verificar la actualización de la entidad Patient
    @Test
    public void testUpdatePatient() {
        p1.setFirstName("Bob");
        p1.setLastName("Smith");
        p1.setAge(31);
        p1.setEmail("bobsmith@gmail.com");
        entityManager.persist(p1);

        Patient foundPatient = entityManager.find(Patient.class, p1.getId());

        assertThat(foundPatient.getId()).isEqualTo(p1.getId());
        assertThat(foundPatient.getFirstName()).isEqualTo(p1.getFirstName());
        assertThat(foundPatient.getLastName()).isEqualTo(p1.getLastName());
        assertThat(foundPatient.getAge()).isEqualTo(p1.getAge());
        assertThat(foundPatient.getEmail()).isEqualTo(p1.getEmail());
    }

    // Test para verificar la eliminación de la entidad Patient
    @Test
    public void testDeletePatient() {
        entityManager.remove(p1);
        Patient foundPatient = entityManager.find(Patient.class, p1.getId());

        assertThat(foundPatient).isNull();
    }

    // Test para verificar la creación de la entidad Room
    @Test
    public void testCreateRoom() {
        Room foundRoom = entityManager.find(Room.class, r1.getRoomName());

        assertThat(foundRoom.getRoomName()).isEqualTo(r1.getRoomName());
    }

    // Test para verificar la eliminación de la entidad Room
    @Test
    public void testDeleteRoom() {
        entityManager.remove(r1);
        Room foundRoom = entityManager.find(Room.class, r1.getRoomName());

        assertThat(foundRoom).isNull();
    }

    // Test para verificar la creación de la entidad Appointment
    @Test
    public void testCreateAppointment() {
        Appointment foundAppointment = entityManager.find(Appointment.class, a1.getId());

        assertThat(foundAppointment.getId()).isEqualTo(a1.getId());
        assertThat(foundAppointment.getPatient().getId()).isEqualTo(a1.getPatient().getId());
        assertThat(foundAppointment.getDoctor().getId()).isEqualTo(a1.getDoctor().getId());
        assertThat(foundAppointment.getRoom().getRoomName()).isEqualTo(a1.getRoom().getRoomName());
        assertThat(foundAppointment.getStartsAt()).isEqualTo(a1.getStartsAt());
        assertThat(foundAppointment.getFinishesAt()).isEqualTo(a1.getFinishesAt());
    }

    // Test para verificar la actualización de la entidad Appointment
    @Test
    public void testUpdateAppointment() {
        a1.setStartsAt(LocalDateTime.of(2023, Month.JUNE, 8, 11, 0));
        a1.setFinishesAt(LocalDateTime.of(2023, Month.JUNE, 8, 12, 0));
        entityManager.persist(a1);

        Appointment foundAppointment = entityManager.find(Appointment.class, a1.getId());

        assertThat(foundAppointment.getId()).isEqualTo(a1.getId());
        assertThat(foundAppointment.getStartsAt()).isEqualTo(a1.getStartsAt());
        assertThat(foundAppointment.getFinishesAt()).isEqualTo(a1.getFinishesAt());
    }

    // Test para verificar la eliminación de la entidad Appointment
    @Test
    public void testDeleteAppointment() {
        entityManager.remove(a1);
        Appointment foundAppointment = entityManager.find(Appointment.class, a1.getId());

        assertThat(foundAppointment).isNull();
    }

}
