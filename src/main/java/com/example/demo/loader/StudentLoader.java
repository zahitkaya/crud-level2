package com.example.demo.loader;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class StudentLoader {

    final StudentRepository studentRepository;


    @PostConstruct
    public void dataInitStudent() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 5);

        Student student1 = Student.builder()
                .firstName("Zahit")
                .lastName("Kaya")
                .email("a@a.com")
                .age(15)
                .signInDate(new Date())

                .build();


        Student student2 = Student.builder()
                .firstName("Mehmet")
                .lastName("Pınar")
                .email("b@b.com")
                .age(16)
                .signInDate(new Date())
                .build();


        studentRepository.save(student1);
        studentRepository.save(student2);
    }
}
