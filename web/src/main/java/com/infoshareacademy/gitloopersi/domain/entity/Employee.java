package com.infoshareacademy.gitloopersi.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
        name = "Employee.findAll",
        query = "SELECT e FROM Employee e"
    )
})
@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "second_name")
  private String secondName;

  @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH,
      CascadeType.PERSIST})
  @JoinColumn(name = "team_id")
  private Team team;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "start_hire_date")
  private LocalDate startHireDate;

  @OneToMany(mappedBy = "employee",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private List<Vacation> employeeVacations = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getStartHireDate() {
    return startHireDate;
  }

  public void setStartHireDate(LocalDate startHireDate) {
    this.startHireDate = startHireDate;
  }

  public List<Vacation> getEmployeeVacations() {
    return employeeVacations;
  }

  public void setEmployeeVacations(
      List<Vacation> employeeVacations) {
    this.employeeVacations = employeeVacations;
  }

  @Override
  public String toString() {
    return "EmployeeResponse{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", secondName='" + secondName + '\'' +
        ", team=" + team +
        ", startDate=" + startDate +
        ", startHireDate=" + startHireDate +
        ", employeeVacations=" + employeeVacations +
        '}';
  }
}