package com.java.springboot.rest1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.springboot.rest1.model.Employee;

@Repository
public interface Mydaorepository extends JpaRepository<Employee, Integer> {

}