## Expense Service

The Expense Service allows users to store expenses by user and checks budgets with the Budget microservice. If the budget exceeds, a notification is sent to the Notification service.

### Prerequisites

- Java 21 or higher
- Gradle

### Setup

1. **Clone the Repository**

   ```sh
   git clone https://github.com/AHannan/rak-expense
   cd rak-expense
   ```

2. **Configure the Database**

   The application is configured to use H2 in-memory database for development. The database configuration is specified in the `application.properties` file. You do not need to make any changes to the database configuration for development.

3. **Build the Project**

   ```sh
   ./gradlew build
   ```

4. **Run the Application**

   ```sh
   ./gradlew bootRun
   ```

### Database Configuration

The application uses H2 in-memory database for development. Below is the relevant section of the `application.properties` file:

```properties
spring.profiles.active=dev
spring.mvc.pathmatch.matching-strategy=ant_path_matcher

server.port=8081
server.servlet.contextPath=/api

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:expense
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Flyway Configuration
spring.flyway.enabled=true
spring.flyway.url=jdbc:h2:mem:expense
spring.flyway.user=sa
spring.flyway.password=password
spring.flyway.schemas=public

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs
```

### Accessing the H2 Console

The H2 console is available at `http://localhost:8081/api/h2-console`. Use the following settings to log in:

- **JDBC URL**: `jdbc:h2:mem:expense`
- **User Name**: `sa`
- **Password**: `password`

### API Endpoints

#### Create Expense

Stores a new expense for a specific user and checks the budget with the Budget microservice. If the budget exceeds, sends a notification to the Notification service.

- **URL**: `/api/expenses`
- **Method**: `POST`
- **Content-Type**: `application/json`
- **Request Body**:

  ```json
  {
      "userId": "abcb72dc-1334-43c5-94ec-ecacaf3309cc",
      "amount": 1500.00,
      "budgetCategoryId": "29b6acb5-b7b3-4c81-87ff-a95b56f094cf",
      "description": "Mid month"
  }
  ```

- **Curl Command**:

  ```sh
  curl --location --request POST 'http://localhost:8081/api/expenses' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "userId": "abcb72dc-1334-43c5-94ec-ecacaf3309cc",
      "amount": 1500.00,
      "budgetCategoryId": "29b6acb5-b7b3-4c81-87ff-a95b56f094cf",
      "description": "Mid month"
  }'
  ```

### Running Tests

To run the tests for the Expense service, execute the following command:

```sh
./gradlew test
```

### Additional Information

For more details, refer to the API documentation available at `http://localhost:8081/api/swagger-ui.html` after starting the application.

### Contributing

If you wish to contribute to this project, please fork the repository and create a pull request with your changes.

### License

This project is licensed under the MIT License.