# Spring-boot-Prime-numbers-Consumer
Demo project of Microservices with Spring Boot - Consumer app. This app accepts random numbers from '**Producer**': https://github.com/avenge79/Spring-boot-Prime-numbers-Producer.git and filters prime numbers if any. Filtered numbers are saved to CSV file with default path '**/temp/consumer**' which can be configured.

## To run projects from command line, make sure you have Java 11 installed and maven. <br/>
To build this app, first execute
**mvn clean install**<br/>
Then go to **'target'** folder and run the project with<br/>
**java -jar consumer-app-1.0.0.jar**<br/>
<br/>
Some properties can be configurd inside '**application.yml**'.

## Run with Docker:<br/>
Make sure you have **Docker** installed. Build project with **Maven** as described above. First create a Docker network for both projects:<br/>
**docker network create consumer-producer**<br/>
Then build Docker image:<br/>
**docker build -t consumer-app -f Dockerfile .**<br/>
After that, use **docker-compose** to run the image using provided **docker-compose.yml**<br/>
**docker-compose up --detach**<br/>
<br/>
The default folder for output CSV files with generated numbers is '**/temp/consumer**'. You can change it in '**application.yml**' inside '**resources**' folder by changing the property<br/>
**csv:
  file:
    folder: /temp/consumer**<br/>
The property for file name is '**name:**'. Make sure files are with different names if using common folder for other project - Producer.<br/>
Numbers will be appended to the file, so make sure if you want clean results to delete or rename previous files or folder before running 'producer' app.
If you want to change it for Docker images, change these in **docker-compose.yml**
