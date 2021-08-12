package com.susankya_USA.Susankya.Tech.Employee.Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.susankya_USA.Susankya.Tech.Employee.Management.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
