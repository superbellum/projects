package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.dao.IEmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRESTController
{
    @Autowired
    private IEmployeeDAO employeeDAO;

    @GetMapping
    public List<Employee> getEmployees()
    {
        return employeeDAO.getEmployees();
    }
}
