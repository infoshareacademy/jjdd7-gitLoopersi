package com.infoshareacademy.gitloopersi.servlet.admin;

import com.infoshareacademy.gitloopersi.entity.Employee;
import com.infoshareacademy.gitloopersi.freemarker.TemplateProvider;
import com.infoshareacademy.gitloopersi.service.EmployeeService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/admin/user")
public class EmployeeManagerServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private EmployeeService employeeService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "user-manager.ftlh");

//    List<Employee> employeeList = employeeService.getEmployeesList();
    List<String> employeeList = new ArrayList<>();
    String e1 = "aaa";
    String e2 = "bbb";

    employeeList.add(e1);
    employeeList.add(e2);

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("employeeList", employeeList);

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//      throws ServletException, IOException {
//    //TODO
//  }
//
//  @Override
//  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
//      throws ServletException, IOException {
//    //TODO
//  }
//
//  @Override
//  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
//      throws ServletException, IOException {
//    //TODO
//  }

}