# Personal Management To-Do List

This is a collaborative Spring Boot web application developed as part of a course project (Group 4). The application serves as a centralized hub for personal organization, consisting of four independent CRUD modules.

## Team & Responsibilities
* **Jimmy** - *Exercise Log (Träningsloggen)*
    * Handles workout activities, duration, and intensity levels.
* **Sedina** - *Grocery List (Matlistan)*
    * Manages shopping items, quantities, and purchase status.
* **Yunus** - *Study Tasks (Studieplanen)*
    * Tracks academic tasks, course names, and deadlines.
* **Fredrik** - *Home Chores (Hushållssysslor)*
    * Categorizes household tasks by room and importance.

## Application Features
The application provides a web interface built with **Thymeleaf** where users can:
- **Create** new items in each respective list.
- **Read** and view detailed information about specific entries.
- **Update** existing records (e.g., marking an item as purchased or changing a deadline).
- **Delete** entries that are no longer needed.

## Technical Stack
* **Java 21** & **Spring Boot 4.x**
* **Spring Data JPA** (PostgreSQL in production, H2 for testing)
* **Thymeleaf** (Frontend templates)
* **Maven** (Build tool)
* **Docker** & **GitHub Actions** (CI/CD)

## Environment Variables
To run this application, the following environment variables must be configured:
* `DB_URL`: PostgreSQL connection string.
* `DB_USERNAME`: Database username.
* `DB_PASSWORD`: Database password.

## Getting Started
1. Clone the repository.
2. Configure the environment variables (DB_URL, DB_USERNAME, DB_PASSWORD).
3. Build and run the application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Access the application at `http://localhost:8080`

## Testing
The project includes automated Unit and Service tests. To run the test suite:
```bash
./mvnw test
```
