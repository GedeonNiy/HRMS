package com.tech.hrms.repository;

import com.tech.hrms.model.Employee;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Object> findById(SingularAttribute<AbstractPersistable, Serializable> id);
}