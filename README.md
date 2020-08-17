# DWP-TechTest

Used Java and Spring Boot to build an API which calls the API at https://bpdts-test-app.herokuapp.com/, and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London. The endpoint returns a JSON list of the relevant users. Built with eclipse.

## Requirements:

Java - 1.8.0

Apache Maven - 4.0.0


## Building and Running:
To build the solution run the following commands from the root folder:

```mvn clean install```

Run: ```java -jar target/technicaltest-0.0.1-SNAPSHOT.jar```

The solution has a GET rest endpoint. To return all users within 50 miles of London, go to:

http://localhost:9090/users

#### Note: If the server does not start, then please use an IDE to start the server
