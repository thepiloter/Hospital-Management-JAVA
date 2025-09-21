package com.ELBADRY.hospitalmanagement;

import java.util.Calendar;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ELBADRY.hospitalmanagement.entities.Doctor;
import com.ELBADRY.hospitalmanagement.entities.Patient;
import com.ELBADRY.hospitalmanagement.repositories.PatientRepository;
import com.ELBADRY.hospitalmanagement.service.IHospitalService;

@SpringBootApplication
public class HospitalManagementApplication  {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HospitalManagementApplication.class, args);
    }

   /* to use add: implements CommandLineRunner

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient( null,"ELBADRY", new Date(), true, 10,null));
        patientRepository.save(new Patient( null,"Hassan", new Date(), false, 20,null));
        patientRepository.save(new Patient( null,"Ali", new Date(), true, 30,null));
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(p -> System.out.println(p.toString()));
        System.out.println("----------------------");
        List<Patient> patientsWithAInThereName= patientRepository.findByNameContains("z");
        patientsWithAInThereName.forEach(p -> System.out.println(p.toString()));
        System.out.println("**********************");
        List<Patient> patientsWithAInThereName2= patientRepository.search("%z%");
        patientsWithAInThereName2.forEach(p -> System.out.println(p.toString()));
        System.out.println("######################");
        List<Patient> patientsWithAInThereName3= patientRepository.findByScoreGreaterThan(10);
        patientsWithAInThereName3.forEach(p -> System.out.println(p.toString()));

    }*/
    @Bean
    CommandLineRunner start(IHospitalService hospitalService) {
        return args -> {
            Stream.of("ELBADRY", "Hassan", "Ali").forEach(name -> {
                Patient patient = new Patient();
                patient.setName(name);

                // BUG: Data Logic Bug - Validação incorreta de data de nascimento
                // Permite criar pacientes com data de nascimento no futuro
                Calendar cal = Calendar.getInstance();
                if (name.equals("Hassan")) {
                    // Bug: Data de nascimento 5 anos no futuro
                    cal.add(Calendar.YEAR, 5);
                } else {
                    // Bug: Usa data atual como nascimento (idade = 0)
                    // Deveria usar uma data no passado
                }
                patient.setBirthDate(cal.getTime());

                patient.setSick(false);
                patient.setScore((int) (Math.random() * 100));
                hospitalService.savePatient(patient);
            });
            Stream.of("Mohammed","Younes","Omar").forEach(name -> {
                Doctor doctor = new Doctor();
                doctor.setName(name);
                doctor.setSpeciality(Math.random()>0.5?"Dentist":"Ophtalmologist");
                hospitalService.saveDoctor(doctor);
            });
        };
    }
}
