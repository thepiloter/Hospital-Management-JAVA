package com.ELBADRY.hospitalmanagement.repositories;

import com.ELBADRY.hospitalmanagement.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
