package com.infoshareacademy.gitLoopersi.vacation;

import static org.apache.commons.lang3.math.NumberUtils.isCreatable;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.gitLoopersi.domain.Employee;
import com.infoshareacademy.gitLoopersi.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeVacationSearcher {

  private ArrayList<Employee> listOfMatchingEmployees;
  private Employee searchedEmployee;

  public void searchForEmployee() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter at least three signs of searched employee name: ");
    String searchedPhrase = scanner.nextLine();
    int characterQuantity = searchedPhrase.length();

    while (characterQuantity < 3) {
      System.out.println("Please enter at least three signs! Enter searched phrase: ");
      searchedPhrase = scanner.nextLine();
      characterQuantity = searchedPhrase.length();
    }

    String finalSearchedPhrase = searchedPhrase;

    listOfMatchingEmployees = (ArrayList<Employee>) EmployeeRepository.getAllEmployees()
        .stream()
        .filter(
            e -> (e.getFirstName().concat(" " + e.getSecondName())).contains(finalSearchedPhrase))
        .collect(Collectors.toList());

    if (listOfMatchingEmployees.size() == 0) {

      System.out.println("There is no matching employee!");

    } else if (listOfMatchingEmployees.size() == 1) {

      searchedEmployee = listOfMatchingEmployees.get(0);

    } else {
      showListOfMatchingEmployeees();
    }

  }

  private void showListOfMatchingEmployeees() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("\nDid you think of...? ");
    Character[] borderStyle = AsciiTable.FANCY_ASCII;

    System.out.println(
        AsciiTable.getTable(borderStyle, getListOfMatchingEmployees(), Arrays.asList(
            createColumn("Index",
                employee -> String
                    .valueOf(getListOfMatchingEmployees()
                        .indexOf(employee) + 1)),
            createColumn("Id", employee -> String.valueOf(employee.getId())),
            createColumn("Name", Employee::getFirstName),
            createColumn("Last Name", Employee::getSecondName),
            createColumn("Team", employee -> employee.getTeam().toString())
        )));

    pickEmployeeFromList(scanner);
  }

  private void pickEmployeeFromList(Scanner scanner) {
    System.out.println("Enter the Id of the employee you wanted to type: ");

    boolean isPickCorrect = false;

    do {
      String pickToCheck = scanner.nextLine();

      while (!isCreatable(pickToCheck)) {
        System.out.print("Wrong data! Enter " +
            "Id of employee you wanted to type: \n");
        pickToCheck = scanner.nextLine();
      }

      int pick = Integer.parseInt(pickToCheck);

      if (pick > 0 && pick <= (getListOfMatchingEmployees().size())) {

        searchedEmployee = getListOfMatchingEmployees().get(pick - 1);
        isPickCorrect = true;

      } else {
        System.out.print("There is no such Id! Enter " +
            "correct Id value of employee you wanted to type: \n");
      }
    } while (!isPickCorrect);
  }

  private ColumnData<Employee> createColumn(String name,
      Function<Employee, String> functionReference) {
    return new Column()
        .header(name)
        .headerAlign(HorizontalAlign.CENTER)
        .dataAlign(HorizontalAlign.LEFT)
        .with(functionReference);
  }

  public List<Employee> getListOfMatchingEmployees() {
    return listOfMatchingEmployees;
  }

  public Employee getSearchedEmployee() {
    return searchedEmployee;
  }
}