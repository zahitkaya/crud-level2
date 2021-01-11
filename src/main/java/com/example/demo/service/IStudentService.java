package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.model.request.StudentRequestDto;
import com.example.demo.model.response.GenericPagedDto;
import com.example.demo.model.response.StudentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    public List<StudentResponseDto> getAllStudents();
    public StudentResponseDto addStudent(StudentRequestDto studentRequest);
    public StudentResponseDto updateStudentById(int id, StudentRequestDto student);
    public void deleteStudentById(int id);
    public StudentResponseDto getStudentById(int id);
    public GenericPagedDto<StudentResponseDto> listInvoice(Pageable page);

}
