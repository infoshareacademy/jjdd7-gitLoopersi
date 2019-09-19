package com.infoshareacademy.gitloopersi.service.employeemanager;

import com.infoshareacademy.gitloopersi.dao.EmployeeDaoBean;
import com.infoshareacademy.gitloopersi.domain.entity.Employee;
import com.infoshareacademy.gitloopersi.domain.entity.Team;
import com.infoshareacademy.gitloopersi.service.teammanager.TeamService;
import com.infoshareacademy.gitloopersi.web.view.EmployeeView;
import com.infoshareacademy.gitloopersi.web.mapper.EmployeeViewMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class EmployeeService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private EmployeeDaoBean employeeDaoBean;

  @EJB
  private TeamService teamService;

  @EJB
  private EmployeeViewMapper employeeViewMapper;

  @Transactional
  public void addEmployee(Employee employee, Long teamId) {
    logger.info("New employee object [{},{}] go to DAO to be saved in DB", employee.getFirstName(),
        employee.getSecondName());
    Team team = teamService.getTeamById(teamId);
    employee.setTeam(team);
    employeeDaoBean.addEmployee(employee);
  }

  @Transactional
  public Employee editEmployee(Employee employee, Long teamId) {
    logger.info("Employee [{}, {}] go to DAO to be modified in DB", employee.getFirstName(),
        employee.getSecondName());
    Team team = teamService.getTeamById(teamId);
    employee.setTeam(team);
    return employeeDaoBean.editEmployee(employee);
  }

  @Transactional
  public Employee getEmployeeById(Long id) {
    logger.info("Employee object id={} go to DAO to be found in DB", id);
    return employeeDaoBean.getEmployeeById(id);
  }

  public void deleteEmployeeById(Long id) {
    logger.info("Employee object id={} go to DAO to be removed in DB", id);
    employeeDaoBean.deleteEmployeeById(id);
  }

  public List<Employee> getEmployeesList() {
    logger.info("Objects employee go to DAO to be found in DB");
    return employeeDaoBean.getEmployeesList();
  }

  @Transactional
  public List<EmployeeView> getEmployeesWithTeamsList() {
    List<EmployeeView> employeeViews = new ArrayList<>();

    getEmployeesList().forEach(e -> {
      employeeViews.add(employeeViewMapper.mapEntityToView(e));
    });

    return employeeViews;
  }
}