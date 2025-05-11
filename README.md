# Hospital Management System

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen" alt="Spring Boot Version"/>
  <img src="https://img.shields.io/badge/Java-17-orange" alt="Java Version"/>
  <img src="https://img.shields.io/badge/MySQL-Database-blue" alt="Database"/>
  <img src="https://img.shields.io/badge/JPA-Hibernate-green" alt="JPA"/>
</p>

<p align="center">
  A modern Spring Boot application for efficient hospital resource management
</p>

## 📋 Overview

This Hospital Management System provides a comprehensive backend solution for healthcare facilities to manage patients, doctors, appointments, and consultations. Built with Spring Boot and JPA, it offers a robust RESTful API architecture for seamless integration with frontend applications.

## 🏥 Key Features

- **Patient Management**: Complete CRUD operations for patient records
- **Doctor Directory**: Manage medical staff and their specialties
- **Appointment Scheduling**: Create, track, and manage patient appointments
- **Consultation Records**: Document medical consultations and reports
- **Status Tracking**: Monitor appointment statuses (PENDING, CANCELED, DONE)

## 🔄 Entity Relationships

The system is designed with the following entity relationships:

- **Patient ➔ Appointments**: One-to-Many relationship
- **Doctor ➔ Appointments**: One-to-Many relationship  
- **Appointment ➔ Consultation**: One-to-One relationship

## 🗃️ Database Schema

The application uses JPA with a relational database to represent the following entities:

### Patient Entity
```java
@Entity
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthDate;
    private boolean sick;
    private int score;
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
}
```

### Doctor Entity
```java
@Entity
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String speciality;
    private String email;
    @OneToMany(mappedBy = "doctor")
    private Collection<Appointment> appointments;
}
```

### Appointment Entity
```java
@Entity
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date appointmentDate;
    @Enumerated(EnumType.STRING)
    private StatusRDV status;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    @OneToOne(mappedBy = "appointment")
    private Consultation consultation;
}
```

### Consultation Entity
```java
@Entity
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateConsultation;
    private Date rapportConsultation;
    @OneToOne
    private Appointment appointment;
}
```

## 🛠️ Technology Stack

- **Spring Boot**: Core framework for building the application
- **Spring Data JPA**: Object-relational mapping
- **Hibernate**: JPA implementation
- **MySQL/H2**: Database options
- **Lombok**: Reduces boilerplate code
- **Maven**: Dependency management

## 🚀 Getting Started

### Prerequisites
- JDK 17 or higher
- Maven
- MySQL (or use the embedded H2 database for testing)

### Installation

1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/Hospital-Management-JAVA.git
   ```

2. Navigate to the project directory
   ```bash
   cd Hospital-Management-JAVA
   ```

3. Build the project
   ```bash
   mvn clean install
   ```

4. Run the application
   ```bash
   mvn spring-boot:run
   ```

5. The API will be available at `http://localhost:8085`

### Database Configuration

The application is pre-configured to use MySQL. You can modify the `application.properties` file to change database settings:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/hospitaldb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update

# Uncomment for H2 in-memory database
# spring.datasource.url=jdbc:h2:mem:hostpitaldb
# spring.h2.console.enabled=true
```

## 📝 API Documentation

The application exposes RESTful endpoints for managing hospital resources:

- **Patients API**: `/api/patients`
- **Doctors API**: `/api/doctors`
- **Appointments API**: `/api/appointments`
- **Consultations API**: `/api/consultations`

## 🧪 Testing

Run the tests with Maven:

```bash
mvn test
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

<p align="center">
  Made with ❤️ for better healthcare management
</p>