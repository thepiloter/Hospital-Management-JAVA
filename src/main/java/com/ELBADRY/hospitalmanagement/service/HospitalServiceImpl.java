package com.ELBADRY.hospitalmanagement.service;

import com.ELBADRY.hospitalmanagement.entities.Appointment;
import com.ELBADRY.hospitalmanagement.entities.Consultation;
import com.ELBADRY.hospitalmanagement.entities.Doctor;
import com.ELBADRY.hospitalmanagement.entities.Patient;
import com.ELBADRY.hospitalmanagement.repositories.AppointmentRepository;
import com.ELBADRY.hospitalmanagement.repositories.ConsultationRepository;
import com.ELBADRY.hospitalmanagement.repositories.DoctorRepository;
import com.ELBADRY.hospitalmanagement.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
        return appointmentRepository.save(a);
    }

    @Override
    public Consultation saveConsultation(Consultation c) {
        return consultationRepository.save(c);
    }
}
