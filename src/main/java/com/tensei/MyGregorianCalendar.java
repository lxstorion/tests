package com.tensei;

import java.util.Arrays;
import java.util.HashMap;

public final class MyGregorianCalendar {

    enum Day {
        SATURDAY,
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
    }

    enum Month {
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER
    }

    // count of days for each month in LEAP year
    static final int[] LEAP_YEAR_MONTH_LENGTH =
            {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // count of days for each month in NON-LEAP year
    static final int[] YEAR_MONTH_LENGTH =
            {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static final int FIRST_ERA_YEAR = 1;
    private static final Day FIRST_ERA_DAY = Day.SATURDAY;

    private boolean isLeap = false;
    private Day firstDayOfCurrentYear;

    private final HashMap<Month, Day[]> calendarMap = new HashMap<>();

    private int currentYear;

    public MyGregorianCalendar(int currentYear) {

        this.currentYear = currentYear <= 1 ? FIRST_ERA_YEAR : currentYear;

        initCalendar();

    }

    private void initCalendar() {
        if (currentYear % 4 == 0)
            isLeap = true;

        setFirstDayOfCurrentYear();

        // initialize Calendar map
        int[] monthsLengths = isLeap ? LEAP_YEAR_MONTH_LENGTH : YEAR_MONTH_LENGTH;

        var currentDayOrdinal = firstDayOfCurrentYear.ordinal();

        for (int i = 0; i < monthsLengths.length; i++) {
            // initialize size of map keys depend on each monthLength elem
            Day[] daysInMonth = new Day[monthsLengths[i]];

            for (int j = 0; j < daysInMonth.length; j++) {
                daysInMonth[j] = Day.values()[currentDayOrdinal];
                if (currentDayOrdinal + 1 >= 7) {
                    currentDayOrdinal = 0;
                    continue;
                }
                currentDayOrdinal++;
            }

            calendarMap.put(Month.values()[i], daysInMonth);
        }
    }

    private void setFirstDayOfCurrentYear() {
        int leapYearsCount = currentYear / 4;
        int nonLeapYearsCount = currentYear - leapYearsCount;

        int totalDayChanges = leapYearsCount * 2 + nonLeapYearsCount;
        firstDayOfCurrentYear = Day.values()[isLeap ? totalDayChanges % 7 - 1 : totalDayChanges % 7];
    }

    public boolean isLeap() {
        return isLeap;
    }

    public void setLeap(boolean leap) {
        isLeap = leap;
    }

    public Day getFirstDayOfCurrentYear() {
        return firstDayOfCurrentYear;
    }

    public void setFirstDayOfCurrentYear(Day firstDayOfCurrentYear) {
        this.firstDayOfCurrentYear = firstDayOfCurrentYear;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var entry : calendarMap.entrySet()) {
            Day[] dayData = entry.getValue();

            int startIndex = dayData[0].ordinal();

            int dayCounter = 1;
            sb.append(String.format("\n\t\t%s", entry.getKey()));
            sb.append("\nSat\tSun\tMon\tTue\tWed\tThu\tFri\n");
            for (int j = 0; j <= startIndex; j++) {
                sb.append(String.format("%s", " "));
            }
            for (Day day : dayData) {
                sb.append(String.format("%3d ", dayCounter++));
                if (dayCounter % 7 == 0) sb.append("\n");
            }

            sb.append('\n');
        }
        return sb.toString();
    }
}
