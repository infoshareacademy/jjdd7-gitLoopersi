package com.infoshareacademy.gitloopersi.menu.menuprint;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.infoshareacademy.gitloopersi.domain.Employee;
import com.infoshareacademy.gitloopersi.menu.Menu;
import com.infoshareacademy.gitloopersi.properties.AppConfig;
import com.infoshareacademy.gitloopersi.repository.EmployeeRepository;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class EmployeesListPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Main menu >> Employees list\n");
//      Copyright 2017 freva
//      Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
//      documentation files (the "Software"), to deal in the Software without restriction, including without limitation
//      the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
//      to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    Character[] borderStyle = AsciiTable.FANCY_ASCII;

    List<Employee> allEmployees = EmployeeRepository.getEmployeeList();

    if ("ASC".equals(AppConfig.getSort())) {
      Collections.sort(allEmployees);
    } else {
      Collections.reverse(allEmployees);
    }

    System.out.println(
        AsciiTable.getTable(borderStyle, allEmployees, Arrays.asList(
            createColumn("Index",
                employee -> String
                    .valueOf(allEmployees
                        .indexOf(employee) + 1)),
            createColumn("Id", employee -> String.valueOf(employee.getId())),
            createColumn("Name", Employee::getFirstName),
            createColumn("Last Name", Employee::getSecondName),
            createColumn("Team", employee -> employee.getTeam().toString()),
            createColumn("Work start date", employee -> employee.getStartDate().format(
                DateTimeFormatter.ofPattern(AppConfig.getDateFormat()))),
            createColumn("Hirement date", employee -> employee.getStartHireDate().format(
                DateTimeFormatter.ofPattern(AppConfig.getDateFormat())))
        )));

    System.out.println("\n1. Add an employee");
    System.out.println("2. Delete an employee");
    System.out.println("0. Return");
    System.out.println("\nType \"exit\" to close the app");
  }

  private ColumnData<Employee> createColumn(String name,
      Function<Employee, String> functionReference) {
    return new Column()
        .header(name)
        .headerAlign(HorizontalAlign.CENTER)
        .dataAlign(HorizontalAlign.LEFT)
        .with(functionReference);
  }
}