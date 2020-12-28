package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.error.ApiExceptionHandler;
import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if (studentRepository.existsStudentByEmail(student.getEmail()))throw new ResponseStatusException(HttpStatus.CONFLICT, "Student already exist");
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudentById(int id,Student student) {
        student.setId(id);
        studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Students","id",id));
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(int id) {
        Student student=studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Students","id",id));
        studentRepository.delete(student);
    }

    @Override
    public Student getStudentById(int id) {
        Student student=studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Students","id",id));
        return student;
    }

    public Page<Student> getPaginatedCharacters(int pageNumber) {
        PageRequest pageable = PageRequest.of(pageNumber - 1, 2);
        Page<Student> resultPage = studentRepository.findAll(pageable);
        if (pageNumber > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException("Not Found Page Number:","pageNumber",pageNumber);
        }
        return resultPage;
    }
//kayıt varsa 409 yoksa 404

}
