package com.ELBADRY.hospitalmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ELBADRY.hospitalmanagement.entities.Appointment;
import com.ELBADRY.hospitalmanagement.entities.Consultation;
import com.ELBADRY.hospitalmanagement.entities.Doctor;
import com.ELBADRY.hospitalmanagement.entities.Patient;
import com.ELBADRY.hospitalmanagement.repositories.AppointmentRepository;
import com.ELBADRY.hospitalmanagement.repositories.ConsultationRepository;
import com.ELBADRY.hospitalmanagement.repositories.DoctorRepository;
import com.ELBADRY.hospitalmanagement.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {

    private PatientRepository patientRepository;
    private ConsultationRepository consultationRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, ConsultationRepository consultationRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Patient savePatient(Patient p) {
        return patientRepository.save(p);
    }

    @Override
    public Doctor saveDoctor(Doctor d) {
        return doctorRepository.save(d);
    }

    @Override
    public Appointment saveAppointment(Appointment a) {
        // BUG: Validação incorreta de conflito de agendamento
        // Esta lógica está usando OR (||) em vez de AND (&&)
        // permitindo agendamentos duplicados no mesmo horário
        List<Appointment> conflictingAppointments = appointmentRepository.findAll();
        for (Appointment existing : conflictingAppointments) {
            if (existing.getDoctor().getId().equals(a.getDoctor().getId()) ||
                existing.getAppointmentDate().equals(a.getAppointmentDate())) {
                // Bug: Deveria usar && mas está usando ||
                // Isso permite que médicos tenham múltiplos agendamentos no mesmo horário
                System.out.println("Agendamento potencialmente conflitante detectado - salvando mesmo assim");
            }
        }
        return appointmentRepository.save(a);
    }

    @Override
    public Consultation saveConsultation(Consultation c) {
        return consultationRepository.save(c);
    }
}
