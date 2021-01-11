package com.example.demo.mapper;

import com.example.demo.decorator.StudentMapperDecorator;
import com.example.demo.entity.Student;
import com.example.demo.model.request.StudentRequestDto;
import com.example.demo.model.response.StudentResponseDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
@DecoratedWith(StudentMapperDecorator.class)
public interface StudentMapper {


    Student studentRequestToStudentEntity(StudentRequestDto request);
    StudentResponseDto studentEntityToStudentDto(Student student);
    StudentResponseDto studentRequestToStudentResponse(StudentRequestDto request);
    Student studentResponseToStudentEntity(StudentResponseDto response);

     StudentRequestDto studentToStudentDtoWithLink(Student student);
}
