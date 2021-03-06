package com.infoshareacademy.gitloopersi.holiday;

import com.infoshareacademy.gitloopersi.domain.Holiday;
import com.infoshareacademy.gitloopersi.parser.TypeOfHoliday;
import com.infoshareacademy.gitloopersi.properties.AppConfig;
import com.infoshareacademy.gitloopersi.repository.HolidayRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;

public class HolidayMapper {

  public void validateCorrectInputDataForHolidayName() {

    HolidayService holidayService = new HolidayService();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Main menu >> Search engine >> Holiday >> By name");
    System.out.println("\nEnter the name of the holiday: ");
    String name = scanner.nextLine();

    List<Holiday> myList = HolidayRepository.getAllHolidays();
    boolean holidayFound = false;
    for (Holiday holiday : myList) {
      if (name.length() >= 3 && holiday.getName().toLowerCase().contains(name.toLowerCase())) {
        holidayService.searchHolidayByName(holiday.getDate(), holiday.getName());
        holidayFound = true;
      }
    }
    if (!holidayFound) {
      System.out.println("\nThere is no holiday");
    }
    System.out.println("\nType '0' to return or 'Enter' to find another holiday.");
  }

  public void validateCorrectInputDataForHolidayDate() {
    System.out.println("Main menu >> Search engine >> Holiday >> By date");
    System.out.println(
        "\nEnter Date from this or next year in format " + AppConfig.getDateFormat() + ":");
    Scanner scanner = new Scanner(System.in);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConfig.getDateFormat());
    LocalDate dateToCheck = null;
    do {
      String tempDateToCheck = scanner.nextLine();
      try {
        dateToCheck = simpleDateFormat
            .parse(tempDateToCheck).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        while (isYearValid(dateToCheck)) {
          System.out.println("\nWrong data! Enter Date from this or next year: ");
          tempDateToCheck = scanner.nextLine();

          dateToCheck = simpleDateFormat.parse(tempDateToCheck)
              .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

      } catch (ParseException e) {
        System.out
            .println(
                "\nWrong data! Please enter data in format " + AppConfig.getDateFormat() + ": ");
      }
    } while (dateToCheck == null);
    List<Holiday> myList = HolidayRepository.getAllHolidays();
    boolean holidayFound = false;
    for (Holiday holiday : myList) {
      if (holiday.getTypeOfHoliday() != TypeOfHoliday.NATIONAL_HOLIDAY) {
        continue;
      }
      if (dateToCheck.equals(holiday.getDate())) {
        System.out.println("\nNon-working day because it is Holiday");
        holidayFound = true;
        break;
      }
    }
    if (!holidayFound) {
      switch (dateToCheck.getDayOfWeek()) {
        case SUNDAY:
          System.out.println("\nNon-working day because it is Sunday");
          break;
        case SATURDAY:
          System.out.println("\nNon-working day because it is Saturday");
          break;
        default:
          System.out.println("\nWorking day");
      }
    }
    System.out.println("\nType '0' to return or 'Enter' to check another date.");
  }

  private boolean isYearValid(LocalDate dateToCheck) {
    return dateToCheck.getYear() < LocalDate.now().getYear()
        || dateToCheck.getYear() > LocalDate.now().plusYears(1).getYear();
  }
}