package com.blp.workshop;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class HotelReservation {
    private List<Hotel> hotels;

    public HotelReservation()
    {
        this.hotels = new ArrayList<Hotel>();
    }

    public void add(Hotel hotel)
    {
        this.hotels.add(hotel);
    }

    public List<Hotel> getHotelList()
    {
        return this.hotels;
    }

    public Map<Integer, Hotel> searchFor(String date1, String date2)
    {
        int totalDays = countTotalDays(date1, date2);
        int weekDays = countWeekDays(date1, date2);
        int weekendDays = totalDays - weekDays;
        return getCheapestHotels(weekDays, weekendDays);
    }

    public Map<Integer, Hotel> getCheapestHotels(int weekDays, int weekendDays)
    {
        Map<Integer, Hotel> hotelCosts = new HashMap<>();
        Map<Integer, Hotel> sortedHotelCosts = new HashMap<>();
        if (hotels.size() == 0)
            return null;
        this.hotels.stream().forEach(
                n -> hotelCosts.put(n.getRegularWeekdayRate() * weekDays + n.getRegularWeekendRate() * weekendDays, n));
        Integer cheap = hotelCosts.keySet().stream().min(Integer::compare).get();
        hotelCosts.forEach((k, v) -> {
            if (k == cheap)
                sortedHotelCosts.put(k, v);
        });
        return sortedHotelCosts;
    }

    public int countTotalDays(String date1, String date2)
    {
        LocalDate startDate = toLocalDate(date1);
        LocalDate endDate = toLocalDate(date2);
        int totalDays = Period.between(startDate, endDate).getDays() + 1;
        return totalDays;
    }

    public int countWeekDays(String date1, String date2)
    {
        LocalDate startDate = toLocalDate(date1);
        LocalDate endDate = toLocalDate(date2);
        Date startDay = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDay = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDay);

        int weekDays = 0;
        while (!calendar.getTime().after(endDay)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if ((dayOfWeek > 1) && (dayOfWeek < 7)) {
                weekDays++;
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return weekDays;
    }

    public LocalDate toLocalDate(String date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
