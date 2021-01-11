package com.example.demo.decorator;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.request.StudentRequestDto;
import com.example.demo.model.response.StudentResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class StudentMapperDecorator implements StudentMapper {
    @Setter(onMethod = @__({@Autowired}))
    StudentMapper studentMapper;


    @Override
    public StudentResponseDto studentEntityToStudentDto(Student student) {

        var responseStudent = studentMapper.studentEntityToStudentDto(student);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 5);
        responseStudent.setYearOfGraduation(c.getTime());

        Link[] links = new Link[]{
                linkTo(methodOn(StudentController.class).addStudent(null))
                        .withRel("student")
                        .withType("POST")
                        .withDeprecation("Add Student"),
                linkTo(methodOn(StudentController.class).listStudents(null))
                        .withRel("student")
                        .withType("GET")
                        .withDeprecation("List Students"),
                linkTo(methodOn(StudentController.class).updateStudent(null,null))
                        .withRel("student")
                        .withType("PUT")
                        .withDeprecation("Update Student")
        };

        responseStudent.add(links);
        return responseStudent;
    }



}
