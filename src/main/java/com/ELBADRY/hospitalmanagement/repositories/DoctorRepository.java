package com.ELBADRY.hospitalmanagement.repositories;

import com.ELBADRY.hospitalmanagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
