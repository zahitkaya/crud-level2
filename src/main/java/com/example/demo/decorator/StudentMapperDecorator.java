package com.example.demo.decorator;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.request.StudentRequestDto;
import com.example.demo.model.response.StudentResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.Calendar;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)

public abstract class StudentMapperDecorator implements StudentMapper {
    @Setter(onMethod=@__({@Autowired}))
    StudentMapper studentMapper;


    @Override
    public StudentResponseDto studentEntityToStudentDto(Student student) {
        var responseStudent=studentMapper.studentEntityToStudentDto(student);
        if (responseStudent.getSignInDate()==null) {
            responseStudent.setSignInDate(Calendar.getInstance().getTime());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, 5);
            responseStudent.setYearOfGraduation(c.getTime());
        }

        //Date date=new Date();
        //responseStudent.setSignInDate(date);
        //responseStudent.setAge(75);
        return responseStudent;
    }

    public String getViewLink(String id) {
        String url = MvcUriComponentsBuilder
                .fromMethodName(StudentController.class, "viewInvoice", id)
                .build().toString();
        return url;

    }

    public String getDownloadLink(String id) {
        String url = MvcUriComponentsBuilder
                .fromMethodName(StudentController.class, "downloadInvoice",id)
                .build().toString();
        return url;

    }
}
