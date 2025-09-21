package com.ELBADRY.hospitalmanagement.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthDate;
    private boolean sick;
    private int score;
    // BUG: Performance Bug - N+1 Query Problem
    // Mudança de LAZY para EAGER causa consultas desnecessárias
    // Cada vez que um Patient é carregado, todos os appointments
    // são carregados automaticamente, mesmo quando não necessário
    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER)
    private List<Appointment> appointments;
}
