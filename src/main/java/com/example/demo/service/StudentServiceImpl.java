package com.example.demo.service;

import com.example.demo.decorator.StudentMapperDecorator;
import com.example.demo.entity.Student;
import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.request.StudentRequestDto;
import com.example.demo.model.response.GenericPagedDto;
import com.example.demo.model.response.StudentResponseDto;
import com.example.demo.repository.StudentRepository;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    final StudentRepository studentRepository;
    final StudentMapper studentMapper;


    @Override
    public List<StudentResponseDto> getAllStudents() {
        var studentList= studentRepository.findAll();
        return studentList.stream()
                .map(student -> studentMapper.studentEntityToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto addStudent(StudentRequestDto studentRequest){
        if (studentRepository.existsStudentByEmail(studentRequest.getEmail()))throw new ResponseStatusException(HttpStatus.CONFLICT, "Student already exist");
        var newStudent=  studentMapper.studentRequestToStudentEntity(studentRequest);
        newStudent.setSignInDate(new Date());
        newStudent=studentRepository.save(newStudent);
        return studentMapper.studentEntityToStudentDto(newStudent);
    }

    @Override
    public StudentResponseDto updateStudentById(int id, StudentRequestDto studentRequestDto) {
        if (studentRepository.findById(id)!=null) throw new ResourceNotFoundException("Students "," id", id);
            var studentResponse = studentMapper.studentRequestToStudentResponse(studentRequestDto);
            studentResponse.setId(id);
            var studentEntity = studentMapper.studentResponseToStudentEntity(studentResponse);
            return studentMapper.studentEntityToStudentDto(studentRepository.save(studentEntity));

    }

    @Override
    public void deleteStudentById(int id) {
        Student student=studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Students","id",id));
        studentRepository.delete(student);
    }

    @Override
    public StudentResponseDto getStudentById(int id) {
        Student student=studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Students","id",id));
        return studentMapper.studentEntityToStudentDto(student);
    }

    @Override
    public GenericPagedDto<StudentResponseDto> listInvoice(Pageable page) {
        Page<Student> invoices = invoices = studentRepository.findAll(page);


        return new GenericPagedDto(invoices
                .stream()
                .map(invoice -> studentMapper.studentEntityToStudentDto(invoice))
                .collect(Collectors.toList()), page,invoices.getTotalElements());
    }
}
