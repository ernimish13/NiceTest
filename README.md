# Prime Factorization Service

## Overview

A Java-based service to compute the **prime factorization** of numbers from an input file.\
Handles invalid inputs and edge cases efficiently.

## Features

 **File Validation** – Ensures the input file is valid, has valid extension(txt or csv) and not empty file before processing  
 **Reads Numbers from a File** – Extracts numerical values efficiently  
 **Computes Prime Factors** – Determines prime factors for each number  
 **Handles Edge Cases Gracefully** – Supports invalid numbers, zero, one, and negative numbers  
 **Skips Blank Lines** – Prevents infinite loops by handling empty lines  
 **Memory-Efficient Processing** – Uses BufferedReader for optimal performance

## Project Structure

```
/src
  ├── main
  │   ├── java
  │   │   ├── com.org.primefactorization
  │   │   │   ├── exception
  │   │   │   │   ├── InvalidInputException.java
  │   │   │   │   ├── InvalidNumberException.java
  │   │   │   │   ├── NegativeNumberException.java
  │   │   │   │   ├── ZeroNumberException.java
  │   │   │   ├── processor
  │   │   │   │   ├── PrimeFactorizationProcessor.java
  │   │   │   ├── service
  │   │   │   │   ├── PrimeFactorizationService.java
  │   │   │   │   ├── impl
  │   │   │   │   │   ├── PrimeFactorizationServiceImpl.java
  │   │   │   ├── utils
  │   │   │   │   ├── FileValidator.java
  │   │   │   ├── MainApplication.java
  │   ├── resources
  │   │   ├── input.txt
  │   │   ├── log4j2.xml
  ├── test
  │   ├── java
  │   │   ├── com.org.primefactorization
  │   │   │   ├── exception
  │   │   │   │   ├── InvalidInputExceptionTest.java
  │   │   │   │   ├── InvalidNumberExceptionTest.java
  │   │   │   │   ├── NegativeNumberExceptionTest.java
  │   │   │   │   ├── ZeroNumberExceptionTest.java
  │   │   │   ├── processor
  │   │   │   │   ├── PrimeFactorizationProcessorTest.java
  │   │   │   ├── service
  │   │   │   │   ├── PrimeFactorizationServiceTest.java
  │   │   │   ├── MainApplicationTest.java
```

## Installation & Usage

### ** Build the Project (Maven)**

```sh
mvn clean install
```

### ** Run the Application**

```sh
java -jar target/PrimeFactorization-1.0-SNAPSHOT.jar <file-path>
```

#### **Example:**

```sh
java -jar target/PrimeFactorization-1.0-SNAPSHOT.jar input.txt
```

## Example Input & Output

### ** Input File (**``**)**

```
6
15
0
-5
1

*
2
a
```

### ** Expected Output**

```

2,3
3,5
2
```

## Technologies Used

- Java 17+
- Maven
- JUnit 5 (for testing)
- SLF4J (for logging)

