# Company-Review-System-and-Job-Portal-Microservice

## Project Structure
### Job Microservice (Port 8081)

- Manages job-related data and operations.
- Interacts with the Company Microservice to get company details for job listings.
- Routes:
  - /jobs - CRUD operations for jobs.
  -/jobs/{id} - Get, update, delete a specific job.
- Database tables: Job, JobDetails.

### Company Microservice (Port 8082)

- Manages company-related data and operations.
- Provides company details for jobs and reviews.
- Routes:
  -/companies - CRUD operations for companies.
  -/companies/{id} - Get, update, delete a specific company.
- Database tables: Company, CompanyDetails.

### Review Microservice (Port 8083)

- Manages reviews related to companies and jobs.
- Routes:
  - /reviews - CRUD operations for reviews.
  - /reviews/{id} - Get, update, delete a specific review.
- Database tables: Review, ReviewDetails.

### API Gateway (Port 8084)

- Acts as a single entry point for all client requests.
- Routes requests to the appropriate microservice.
- Handles load balancing, authentication, and security.
- Spring Cloud Config Server (Port 8888)

- Centralized configuration management for all microservices.
- Stores configuration files for all environments.
- Provides configuration properties to microservices.

### Eureka Server (Port 8761)

- Service discovery server where all microservices register themselves.
- Provides information about available instances of microservices.

### Zipkin Server (Port 9411)

- Distributed tracing system to monitor and troubleshoot microservice interactions.
- Collects and displays tracing data.

### PostgreSQL Server (Port 5432)

- Primary relational database for storing persistent data.
- Database for all microservices.

### PgAdmin (Port 5050)

- Database management tool for PostgresSQL.
- Provides a web interface for database management.

### RabbitMQ Server (Port 5672)

- Message broker for asynchronous communication between microservices.
- Supports message queuing, routing, and delivery.

### RabbitMQ UI (Port 15672)

- Management interface for RabbitMQ.
- Allows monitoring and managing message queues.