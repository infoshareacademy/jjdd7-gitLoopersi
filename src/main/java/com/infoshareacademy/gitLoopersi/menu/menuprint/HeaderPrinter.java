package com.infoshareacademy.gitLoopersi.menu.menuprint;

import com.infoshareacademy.gitLoopersi.menu.Menu;

public class HeaderPrinter implements Menu {

  @Override
  public void doAction() {
    System.out.println("Main menu");
    System.out.println("\n1. Employees list");
    System.out.println("2. Teams list");
    System.out.println("3. Vacation");
    System.out.println("4. Search");
    System.out.println("5. Configuration");
    System.out.println("\nType \"exit\" to close the app");
  }
}
