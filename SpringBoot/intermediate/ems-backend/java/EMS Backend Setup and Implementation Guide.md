#  ems-backend-java implementation guide

## **Step-1: Initiating the Project form [spring initializer](https://start.spring.io/)**

* **Project Type**: Maven  
* **Language**:	    Java  
* **Spring Boot**: 	3.4.0 (LTS)  
* **Project Metadata**:   
  - **Group**: 		    com.krz  
  - **Artifact**: 	  ems-backend-java  
  - **Name**: 		    ems-backend-java  
  - **Description**: 	Employee Management System service built with java.  
  - **Package**:	    com.krz.ems-backend-java  
  - **Packaging**: 	  Jar  
  - **Java**: 		    17  
* **Dependencies**:  
  - Spring Web Web  
  - Spring Data JPA SQL  
  - MySQL Driver SQL  
  - Lombok Developer Tools

## **Step-2: Importing the downloaded project into Intellij/vs code**

* Unzip the project downloaded from the previous step.  
* Open the project from intellij/vs code.  
* **Intellij**: Add project configuration if needed as **Application.** Specify the **JDK** & **MainActivity** there.  
* **Vs Code**: Install Spring Boot Extension Pack if needed  
* Now the project should be run from intellij/vscode.

## **Step-3: DB Connection**

* Created a database from **phpmyadmin** or mysql **workbench**.  
* Open the **application.properties** file from **src/main/resources** and add the JDBC connection details like below:


```
#DB Connection Details  
spring.datasource.url=jdbc:mysql://localhost:3306/ems_db  
spring.datasource.username=root  
spring.datasource.password=

#Hibernate configs  
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect  
spring.jpa.hibernate.ddl-auto=update
```

## **Step-4: Creating the project architecture**

* Create the below **packages** in ***src/main/java/com.krz.ems\_backend\_java***  
1. ***controller***  
2. ***dto***  
3. ***entity***  
4. ***exception***  
5. ***mapper***  
6. ***repository***  
7. ***service***  
8. ***service/impl***

## **Step-5: Creating the Entities**
```
@Getter  
@Setter  
@AllArgsConstructor  
@NoArgsConstructor  
@Entity  
@Table(name = "employees")  
public class Employee {  
   @Id  
   @GeneratedValue(strategy = GenerationType.IDENTITY)  
   private Long id;

   @Column(name = "full_name")  
   String fullName;

   @Column(nullable = false, unique = true)  
   String email;

}
```
## **Step-6: Creating the Repository**
```
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {  
}
```
## **Step-7: Creating the DTOs**
```
@Getter  
@Setter  
@NoArgsConstructor  
@AllArgsConstructor  
public class EmployeeDto {  
   private Long id;  
   String fullName;  
   String email;  
}
```
## **Step-8: Creating the Mappers**
```
public class EmployeeMapper {  
   public static Employee mapEmployeeDtoToEmployee(EmployeeDto employeeDto) {  
       return new Employee(  
               employeeDto.getId(),  
               employeeDto.getFullName(),  
               employeeDto.getEmail()  
       );  
   }

   public static EmployeeDto mapEmployeeToEmployeeDto(Employee employee) {  
       return new EmployeeDto(  
               employee.getId(),  
               employee.getFullName(),  
               employee.getEmail()  
       );  
   }  
}
```
## **Step-9: Creating the Service interface and Service Implementation**
```
public interface IEmployeeService {  
   EmployeeDto createEmployee(EmployeeDto employeeDto);  
}

@Service  
@AllArgsConstructor  
public class EmployeeServiceImpl implements IEmployeeService {  
   private IEmployeeRepository employeeRepository;

   @Override  
   public EmployeeDto createEmployee(EmployeeDto employeeDto) {  
       Employee employee = EmployeeMapper.mapEmployeeDtoToEmployee(employeeDto);  
       Employee savedEmployee = employeeRepository.save(employee);

       return EmployeeMapper.mapEmployeeToEmployeeDto(savedEmployee);  
   }  
)
```
## **Step-10: Creating the controller**
```
@CrossOrigin("*")  
@AllArgsConstructor  
@RestController  
@RequestMapping("api/v1/employees")  
public class EmployeeController {  
   private IEmployeeService employeeService;

   // Create an employee*  
   @PostMapping  
   public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {  
       EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);  
       return ResponseEntity.ok(savedEmployee);  
   }  
}
```
## **Step-11: Adding Update, Delete and Read APIs with Exception handling**
```
@ResponseStatus(value = HttpStatus.NOT_FOUND)  
public class ResourceNotFoundException extends RuntimeException {  
 public ResourceNotFoundException(String message) {  
   super(message);  
 }  
}
```
## **Step-11: Test API**

* Use **Postman** to test the `POST` endpoint at `http://localhost:8080/api/v1/employees`.  
* Example request body:
```
{
  "id": null,
  "fullName": "John Doe",
  "email": "john.doe@example.com"
}
```

## **Step-12: Custom Error Handling**

- Add a generic exception model class.
- Add an exception class for each exception type.
- Handle them from a Global exception handler.

```
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
 private LocalDateTime timestamp;
 private int status;
 private String message;
 private String path;
}
```

```
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
 public ResourceNotFoundException(String message) {
   super(message);
 }
}
```

```
@ControllerAdvice
public class GlobalExceptionHandler {


 //Generic exception handler
 @ExceptionHandler(Exception.class)
 public ResponseEntity<?> handleGenericException(Exception ex, WebRequest request) {
   ErrorDetails errorDetails = new ErrorDetails(
       LocalDateTime.now(),
       HttpStatus.INTERNAL_SERVER_ERROR.value(),
       ex.getMessage(),
       request.getDescription(false));


   return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
 }


 // Handle ResourceNotFoundException globally
 @ExceptionHandler(ResourceNotFoundException.class)
 public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
   // Create the error response with timestamp
   ErrorDetails errorDetails = new ErrorDetails(
       LocalDateTime.now(),
       HttpStatus.NOT_FOUND.value(),
       ex.getMessage(),
       request.getDescription(false));


   // Return the error response with additional message
   return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
 }
}
```
