# Spring Boot Quiz Application

A simple Spring Boot application that allows users to take a quiz, submit answers, and view session summaries.

## Prerequisites

- **Java 17** or later
- **Maven**
- **MySQL** (or any other database, MySQL is used here)

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/your-username/quiz-application.git
```

### 2. Set up MySQL Database

Create a MySQL database:

```sql
CREATE DATABASE quiz_db;
```

Run this SQL script to create the `question` table:

```sql
CREATE TABLE IF NOT EXISTS question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(255) NOT NULL,
    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100) NOT NULL,
    option_d VARCHAR(100) NOT NULL,
    correct_option CHAR(1) NOT NULL
);
```

### 3. Configure `application.properties`

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/quiz_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 4. Build and Run

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

The application will run on port `8080`.

## API Endpoints

1. **Start Session**: `POST /api/quiz/start`
2. **Get Random Question**: `GET /api/quiz/question/random/{sessionId}`
3. **Submit Answer**: `POST /api/quiz/answer/{sessionId}`
4. **Get Session Summary**: `GET /api/quiz/summary/{sessionId}`
5. **Manage Questions**:
   - Create/Update: `POST /api/quiz/question`
   - Get All: `GET /api/quiz/questions`
   - Delete: `DELETE /api/quiz/question/{id}`

## Testing

Run tests with:

```bash
mvn test
```

---