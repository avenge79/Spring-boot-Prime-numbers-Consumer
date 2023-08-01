# Spring-boot-Prime-numbers-Consumer
Demo project of Microservices with Spring Boot - Consumer app
This app is part of my other project - Producer. It consumer numbers through REST or Websocket, filters prime numbers and writes numbers to CSV file.

## To run projects from command line, make sure you have Java 11 installed and maven. <br/>
First run the consumer - from '**consumer**' folder, type<br/>
**mvn clean install**<br/>
Then go to **'target'** folder and run the project with<br/>
**java -jar consumer-0.0.1-SNAPSHOT.jar**<br/>
After that, run the producer app to send the numbers to consumer - go to **'producer'** folder and again type<br/>
**mvn clean install**<br/>
to build the project. Then from folder '**target**' run the app:<br/>
**java -jar producer-0.0.1-SNAPSHOT.jar**<br/>
<br/>
Some properties can be configurd inside '**application.properties**' for both applications.

## Run with Docker:<br/>
Make sure you have **Docker** installed. Build both projects with **Maven** as described above. First create a Docker network for both projects:<br/>
**docker network create consumer-producer**<br/>
Then build image for consumer from **'consumer'** folder:<br/>
**docker build -t consumer-app -f Dockerfile .**<br/>
After that, use **docker-compose** to run the image using provided **docker-compose.yml**<br/>
**docker-compose up --detach**<br/>
Do the same for the producer - go to **'producer'** folder:<br/>
**docker build -t producer-app -f Dockerfile .**<br/>
And then with **docker-compose**:<br/>
**docker-compose up --detach**<br/>
you should have both projects running.<br/>
<br/>
The default folder for output CSV files with generated numbers is '**/temp**'. You can change it in '**application.properties**' for both projects by changing the property<br/>
**csv.file.folder=/temp**<br/>
The property for file name is '**csv.file.name**' for both producer and consumer or you can set different folders. Make sure files are with different names if using common folder.<br/>
Numbers will be appended to files, so make sure if you want clean results to delete files or folder before running 'producer' app.
If you want to change it for Docker images, change these in **docker-compose.yml**

Integration tests can be run with
**mvn test**
Make sure '**consumer**' is running.
