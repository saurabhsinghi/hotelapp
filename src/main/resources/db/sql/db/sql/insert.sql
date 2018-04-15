INSERT INTO HOTEL_CONFIG(id, hotelName, address, city, state, country, zip,roomCount, overbookingLevel, customerId)
 VALUES (100, 'Hilton', '123 H St.', 'San Francisco', 'CA', 'USA', '94105', 100, 10,'HSF36901');
INSERT INTO HOTEL_CONFIG(id, hotelName, address, city, state, country, zip,roomCount, overbookingLevel, customerId)
 VALUES (101, 'Hyatt', '456 Y St.', 'Los Angeles', 'CA', 'USA', '90034', 250, 15,'YLA10960');

INSERT INTO HOTEL_RESERVATION(hotelId, guestFirstName, guestLastName, guestEmail, checkIn, checkOut)
 VALUES(100,'Mike', 'Robins', 'mr@gmail.com',current_timestamp, timestampadd(SQL_TSI_DAY, 2, current_timestamp));
INSERT INTO HOTEL_RESERVATION(hotelId, guestFirstName, guestLastName, guestEmail, checkIn, checkOut)
 VALUES(100,'John', 'Oliver', 'jo@gmail.com',timestampadd(SQL_TSI_DAY, -2, current_timestamp),timestampadd(SQL_TSI_DAY, 2, current_timestamp));
INSERT INTO HOTEL_RESERVATION(hotelId, guestFirstName, guestLastName, guestEmail, checkIn, checkOut)
 VALUES(100,'Tim', 'Martin', 'tm@gmail.com',timestampadd(SQL_TSI_DAY, -5, current_timestamp), current_timestamp);
INSERT INTO HOTEL_RESERVATION(hotelId, guestFirstName, guestLastName, guestEmail, checkIn, checkOut)
 VALUES(100,'Larry', 'Page', 'lp@gmail.com',timestampadd(SQL_TSI_DAY, 30, current_timestamp),timestampadd(SQL_TSI_DAY, 35, current_timestamp));
