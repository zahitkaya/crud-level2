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
    @Mapping(source = "graduateYear",target = "yearOfGraduation")
    StudentResponseDto studentEntityToStudentDto(Student student);
    @Mapping(source = "graduateYear",target = "yearOfGraduation")
    StudentResponseDto studentRequestToStudentResponse(StudentRequestDto request);
    @Mapping(source = "yearOfGraduation",target = "graduateYear")
    Student studentResponseToStudentEntity(StudentResponseDto response);

     StudentRequestDto studentToStudentDtoWithLink(Student student);
}
