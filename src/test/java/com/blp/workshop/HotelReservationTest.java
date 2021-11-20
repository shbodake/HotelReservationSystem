package com.blp.workshop;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HotelReservationTest {

    @Test
    public void whenHotelAddedToSystemShouldGetAdded()
    {
        Hotel hotel1 = new Hotel("Lakeewood", 110, 90, 80, 80, 3);
        Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        Hotel[] hotelList = {hotel1, hotel2, hotel3};
        List<Hotel> hotels = Arrays.asList(hotelList);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        List<Hotel> result = hotelReservation.getHotelList();
        assertEquals(hotels, result);
    }

    @Test
    public void whenGivenDateRangeShouldReturnCheapestHotel()
    {
        Hotel hotel1 = new Hotel("Lakeewood", 110, 90, 80, 80, 3);
        Hotel hotel2 = new Hotel("Bridgewood", 160, 60, 110, 50, 4);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.add(hotel1);
        hotelReservation.add(hotel2);
        hotelReservation.add(hotel3);
        Map<Integer, Hotel> result = hotelReservation.searchFor("2020-09-11", "2020-09-12");
        result.forEach((k, v) -> System.out.println(v.getName() + " " + k));
        assertNotNull(result);
    }
}