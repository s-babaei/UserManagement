package com.example.userManagment.repository;

import com.example.userManagment.model.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EmployeeRepository extends JpaRepository<Manager, Long> {


}
