package com.hotels.util;

import com.hotels.exception.InvalidInputException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  public static long getTimeInMsFromString(String strDate) throws InvalidInputException{
    long retVal = 0;
    try{
      Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
      retVal = date1.getTime();
    }
    catch (Exception e){
     throw new InvalidInputException("Date string not in required format, YYYY-MM-DD ("+ strDate+")");
    }
    return retVal;
  }

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  public static boolean validateEmail(String emailStr) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
    return matcher.find();
  }

}

