package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

/**
 * 生徒情報
 */
@Repository
public interface UserRepository extends JpaRepository<Student,Long> {

}
