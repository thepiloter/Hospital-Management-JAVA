package com.ELBADRY.hospitalmanagement.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ELBADRY.hospitalmanagement.entities.Patient;
import com.ELBADRY.hospitalmanagement.repositories.PatientRepository;

@RestController
public class PatientRestService {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    @GetMapping("/patients/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        // BUG: Vulnerabilidade de segurança - NullPointerException
        // Usa .get() diretamente sem verificar se Optional existe
        // Isso permite ataques DoS através de IDs inválidos
        Patient patient = patientRepository.findById(id).get();

        // BUG: Acesso não validado a dados sensíveis
        // Retorna diretamente sem verificar se o paciente existe
        // ou se o usuário tem permissão para acessar os dados
        System.out.println("Acessando dados do paciente: " + patient.getName());
        return patient;
    }
}
