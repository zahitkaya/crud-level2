package com.example.demo.repository;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Characters;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    public boolean existsStudentByEmail(String email);
    Page<Student> findAll(Pageable pageable);

}
