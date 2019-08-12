package com.infoshareacademy.jjdd7;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class SearchFreeDay {


    public static void main(String[] args) {

        System.out.println("Enter Date in format yyyy.MM.dd :");

        Scanner input = new Scanner(System.in);

        String date = input.nextLine();
        System.out.println(date);
        DateTimeFormatter dateFotmater = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate dateGiven = null;
        Pattern datePattern = compile("[1-2][0,1,9][0-9][0-9]\\.[0-1][0-9]\\.[0-3][0-9]");
        Matcher matcher = datePattern.matcher(date);
            if (matcher.matches()) {
                try {
                    dateGiven = LocalDate.parse(date, dateFotmater);
                } catch (DateTimeParseException e) {
                    System.out.println("Parse error occured");
                }
            } else {
                System.out.println("Wrong data please enter data in format yyyy.MM.dd: ");
            }
            ParserImpl parser = new ParserImpl("HolidaysApi.json");
            List<Holiday> myList = parser.getListOfHolidays();
            boolean ifHolidayfound = false;
            for (Holiday holiday : myList) {
                if (holiday.getType() != Type.NATIONAL_HOLIDAY) {
                    continue;
                }
                if (dateGiven.equals(holiday.getDate())) {
                    System.out.println("Non-working day because it is Holiday");
                    ifHolidayfound = true;
                    break;
                }
            }
            if (!ifHolidayfound) {
                switch (dateGiven.getDayOfWeek()) {
                    case SUNDAY:
                        System.out.println("Non-working day because it is Sunday");
                        break;
                    case SATURDAY:
                        System.out.println("Non-working day because it is Saturday");
                        break;
                    default:
                        System.out.println("Working day");
                }
            }
    }
}
