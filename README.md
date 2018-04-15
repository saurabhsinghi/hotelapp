# Hotel Reservation
Mini project to implement a system that allows a configured level of overbooking.

# Installation
  Prerequisites: `Java 1.7+, Maven 3.5.0+, Docker`. 
  Steps:
   1. Clone this repo
   2. cd to the installation directory
   3. `mvn package` (This will run JUnit tests and prepare the war file)
   4. `docker build . -f  Dockerfile -t hotelapp` (This will create the docker image)
   5. `docker run -p 5000:8080 -t hotelapp` (This will run the application on port 5000)
   
Quickly test if you can access the application by typing in `http://localhost:5000/hotelapp/` in the browser. You should see a welcome message - "Welcome to the Hotel App!"
   
   
# Database
I have used an embedded relational DB called HSQL for the purpose of this project. Below is the data schema of the tables used:
```
HOTEL_CONFIG (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100),
  hotelName varchar(1000),
  address varchar(4000),
  city varchar (200),
  state varchar (2),
  country varchar (100),
  zip varchar(10),
  roomCount INTEGER,
  overbookingLevel FLOAT,
  customerId varchar (64) -- The cust_id is the external hotel id that would be pre-populated when the hotel client is on-boarded.
);
```
```
HOTEL_RESERVATION(
 id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100),
 hotelId INTEGER,
 guestFirstName varchar(50),
 guestLastName varchar(50),
 guestEmail varchar(200),
 checkIn TIMESTAMP,
 checkOut TIMESTAMP
);
```
When the application loads up it creates the necessary tables and inserts some test data (via create.sql & insert.sql) for the APIs to function:

HOTEL_CONFIG
```
---+-------------+-----------+---------------+-------+--------+------+---------+----------------+----------+
| ID  | HOTELNAME| ADDRESS   | CITY          | STATE | COUNTRY| ZIP  | ROOMCOUNT OVERBOOKINGLEVEL CUSTOMERID
+-----+----------+-----------+---------------+-------+--------+------+---------+----------------+----------+
| 100 | Hilton   | 123 H St. | San Francisco | CA    | USA    | 94105| 100     | 10.0           | HSF36901 |
| 101 | Hyatt    | 456 Y St. | Los Angeles   | CA    | USA    | 90034| 250     | 15.0           | YLA10960 |
+-----+----------+-----------+---------------+-------+--------+------+---------+----------------+----------+
```
HOTEL_RESERVATION
```
+-----+-----+-------+--------+--------------+-------------------------+-------------------------+
| 100 | 100 | Mike  | Robins | mr@gmail.com | 2018-04-15 08:54:50.998 | 2018-04-17 08:54:50.998 |
| 101 | 100 | John  | Oliver | jo@gmail.com | 2018-04-13 08:54:51.001 | 2018-04-17 08:54:51.001 |
| 102 | 100 | Tim   | Martin | tm@gmail.com | 2018-04-10 08:54:51.002 | 2018-04-15 08:54:51.002 |
| 103 | 100 | Larry | Page   | lp@gmail.com | 2018-05-15 08:54:51.003 | 2018-05-20 08:54:51.003 |
+-----+-----+-------+--------+--------------+-------------------------+-------------------------+
```
