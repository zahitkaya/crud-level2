package com.example.demo.service;

import com.example.demo.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IStudentService {
    public List<Student> getAllStudents();
    public Student addStudent(Student student);
    public Student updateStudentById(int id,Student student);
    public void deleteStudentById(int id);
    public Student getStudentById(int id);
    public Page<Student> getPaginatedCharacters(int pageNumber);
}
