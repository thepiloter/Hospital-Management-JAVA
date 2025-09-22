# 🏥 Hospital Management System - Java Spring Boot

A comprehensive hospital management system built with **Spring Boot 3.4.3** and **Java 17**, featuring patient registration, doctor management, appointment scheduling, and medical consultations. This application demonstrates JPA relationships, REST APIs, and includes both H2 (development) and MySQL (production) database support.

## 🚀 Quick Start (Docker - Recommended)

**Zero-configuration setup! Just Docker required.**

```bash
# Clone the repository
git clone https://github.com/thepiloter/Hospital-Management-JAVA.git
cd Hospital-Management-JAVA

# One-command setup (Windows)
docker-setup.bat run

# One-command setup (macOS/Linux)
chmod +x docker-setup.sh && ./docker-setup.sh run

# Or manually with Docker Compose
docker-compose up --build -d
```

**Access the application:**
- 🌐 **REST API**: http://localhost:8085/patients
- 🗄️ **H2 Database Console**: http://localhost:8085/h2-console
- 📊 **Individual Patient**: http://localhost:8085/patients/1

## 🛠️ Alternative Setup (Manual)

### Prerequisites
- **Java 17+**
- **Maven 3.6+**

### Run Locally
```bash
# Clone and navigate
git clone https://github.com/thepiloter/Hospital-Management-JAVA.git
cd Hospital-Management-JAVA

# Run with Maven (H2 database pre-configured)
mvn spring-boot:run

# Or build and run JAR
mvn clean package
java -jar target/Hospital-Management-0.0.1-SNAPSHOT.jar
```

## 🏗️ Architecture Overview

### **Technology Stack**
- **Backend**: Spring Boot 3.4.3, Spring Data JPA, Hibernate
- **Database**: H2 (development), MySQL 8.0+ (production)
- **Build Tool**: Maven 3.9+
- **Java Version**: 17 (LTS)
- **Containerization**: Docker & Docker Compose

### **Core Features**
- 👥 **Patient Management**: Complete patient lifecycle with medical history
- 👨‍⚕️ **Doctor Profiles**: Specialization-based management and availability
- 📅 **Appointment Scheduling**: Time-slot management with conflict detection
- 📋 **Medical Consultations**: Documentation and follow-up tracking
- 🔗 **REST API**: JSON endpoints for external integration
- 🗄️ **Dual Database**: H2 for development, MySQL for production

### **Database Schema**
```
Patient (1) ←→ (N) Appointment (N) ←→ (1) Doctor
                       ↓ (1:1)
                   Consultation
```

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/patients` | Get all patients |
| `GET` | `/patients/{id}` | Get patient by ID |
| `GET` | `/h2-console` | H2 Database web interface |

### Example API Response
```json
[
  {
    "id": 1,
    "name": "ELBADRY",
    "birthDate": "2030-09-21T00:26:05.000+00:00",
    "sick": false,
    "score": 42,
    "appointments": []
  }
]
```

## 🐳 Docker Configuration

### **Container Features**
- ✅ **Multi-stage build** for optimized image size
- ✅ **Non-root user** for security
- ✅ **Health checks** for reliability
- ✅ **Alpine Linux** for minimal footprint
- ✅ **Automatic restarts** unless stopped

### **Docker Commands**
```bash
# Build and start (recommended)
docker-setup.bat run          # Windows
./docker-setup.sh run         # macOS/Linux

# Individual commands
docker-setup.bat build        # Build image
docker-setup.bat start        # Start container
docker-setup.bat stop         # Stop container
docker-setup.bat logs         # View logs
docker-setup.bat cleanup      # Remove all resources

# Manual Docker commands
docker-compose up --build -d  # Build and run
docker-compose logs -f        # View logs
docker-compose down           # Stop and remove
```

## 🗄️ Database Configuration

### **H2 Database (Default)**
- **URL**: `jdbc:h2:mem:hospitaldb`
- **Console**: http://localhost:8085/h2-console
- **Username**: `sa`
- **Password**: (empty)
- **Benefits**: Zero setup, perfect for development

### **MySQL Database (Production)**
```properties
# Update src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospitaldb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## 📁 Project Structure

```
Hospital-Management-JAVA/
├── src/main/java/com/ELBADRY/hospitalmanagement/
│   ├── entities/           # JPA Entities (Patient, Doctor, Appointment)
│   ├── repositories/       # Spring Data JPA Repositories
│   ├── service/           # Business Logic Layer
│   ├── web/               # REST Controllers
│   └── HospitalManagementApplication.java
├── src/main/resources/
│   └── application.properties
├── docker-compose.yml     # Docker orchestration
├── Dockerfile            # Container definition
├── docker-setup.bat     # Windows automation script
├── docker-setup.sh      # macOS/Linux automation script
└── pom.xml              # Maven configuration
```

## � Development

### **Sample Data**
The application automatically creates sample data on startup:
- **Patients**: ELBADRY, Hassan, Ali (with interesting birth date variations)
- **Doctors**: Mohammed, Younes, Omar (random specializations)

### **Key Classes**
- `Patient.java`: Patient entity with appointment relationships
- `Doctor.java`: Doctor profiles with specializations
- `Appointment.java`: Appointment scheduling system
- `HospitalServiceImpl.java`: Core business logic
- `PatientRestService.java`: REST API endpoints

### **Building from Source**
```bash
# Compile and test
mvn clean compile

# Run tests
mvn test

# Package as JAR
mvn clean package

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=production
```

## 🚨 Troubleshooting

### **Common Issues**

**Port 8085 already in use:**
```bash
# Check what's using the port
netstat -an | findstr :8085

# Change port in application.properties
server.port=8086
```

**Docker issues:**
```bash
# Check Docker status
docker info

# Rebuild containers
docker-compose build --no-cache

# View container logs
docker-compose logs hospital-management
```

**Java version problems:**
```bash
# Check Java version
java -version

# Verify JAVA_HOME
echo $JAVA_HOME  # macOS/Linux
echo %JAVA_HOME% # Windows
```

### **Performance Tuning**
```bash
# Increase JVM memory (Docker)
JAVA_OPTS=-Xmx1024m -Xms512m

# Increase JVM memory (Local)
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx1024m"
```

## � Monitoring & Health

### **Health Checks**
- **Docker**: Automatic health checks every 30s
- **Manual**: `curl http://localhost:8085/patients`
- **Database**: Access H2 console to verify data

### **Logs**
```bash
# Docker logs
docker-compose logs -f

# Application logs (local)
tail -f logs/application.log
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

## 📜 License

This project is open source and available under the [MIT License](LICENSE).

## 🎯 Perfect for Learning

This repository demonstrates:
- ✅ **Spring Boot** best practices
- ✅ **JPA/Hibernate** relationships
- ✅ **REST API** development
- ✅ **Docker** containerization
- ✅ **Database** integration (H2 + MySQL)
- ✅ **Maven** project structure
- ✅ **Production-ready** configuration

---

**Made with ❤️ for learning and development**