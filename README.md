# Hotel Reservation
Mini project to implement a system that allows a configured level of overbooking.

# Installation
  Prerequisites: Java 1.7+, Maven 3.5.0+, Docker. 
  Steps:
   1. Clone this repo
   2. cd to the installation directory
   3. mvn package (This will run JUnit tests and prepare the war file)
   4. docker build . -f  Dockerfile -t hotelapp (This will create the docker image)
   5. docker run -p 5000:8080 -t hotelapp (This will run the docker image on port 5000)
   
   

#Database

#API
