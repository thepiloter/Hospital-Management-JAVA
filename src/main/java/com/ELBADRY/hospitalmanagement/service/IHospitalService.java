package com.ELBADRY.hospitalmanagement.service;

import com.ELBADRY.hospitalmanagement.entities.Appointment;
import com.ELBADRY.hospitalmanagement.entities.Consultation;
import com.ELBADRY.hospitalmanagement.entities.Doctor;
import com.ELBADRY.hospitalmanagement.entities.Patient;

public interface IHospitalService {

    Patient savePatient(Patient p);

    Doctor saveDoctor(Doctor d);

    Appointment saveAppointment(Appointment a);

    Consultation saveConsultation(Consultation c);
}
