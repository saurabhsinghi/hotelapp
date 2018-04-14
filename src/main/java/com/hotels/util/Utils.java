package com.hotels.util;

/**
 * © 2015 – 2018. Talix, Inc. All rights reserved.
 *
 * @author: ssinghi
 */
public class Utils {

  public static final String CONFIG_SUCCESSFUL_MESSAGE = "Configuration successful";
  public static final String RESERVATION_SUCCESSFUL_MESSAGE = "Reservation successful";

  public static final String SQL_SET_CONFIG =
      " UPDATE HOTEL_CONFIG SET roomCount=:rooms, overbookingLevel=:level WHERE id=:hotelId;";

  public static final String SQL_ADD_RESERVATION =
            "insert into HOTEL_RESERVATION(hotelId, guestFirstName, guestLastName, guestEmail, checkIn, checkOut) \n"
            +" WITH X as(\n"
            +"     select roomCount * ((overbookingLevel  + 100)/100)  as totalBookings\n"
            +"     from HOTEL_CONFIG\n"
            +"    where id =:hotelId \n"
            +"   )\n"
            +"  ,Y as(\n"
            +"     select count(*) as currentBookings\n"
            +"     from HOTEL_RESERVATION\n"
            +"     where(checkIn, checkOut)\n"
            +"    overlaps (TO_TIMESTAMP(:checkIn,'YYYY-MM-DD'), TO_TIMESTAMP(:checkOut,'YYYY-MM-DD')) \n"
            +"    and hotelId =:hotelId \n"
            +"   )\n"
            +"   select :hotelId, :fName, :lName, :email, TO_TIMESTAMP(:checkIn,'YYYY-MM-DD'), TO_TIMESTAMP(:checkOut,'YYYY-MM-DD') from (\n"
            +"    select\n"
            +"     (case \n"
            +"       when (totalBookings - currentBookings) > 0 then 1  \n"
            +"       end) as value\n"
            +"       from X ,Y\n"
            +"   ) where value is not null\n";
}
