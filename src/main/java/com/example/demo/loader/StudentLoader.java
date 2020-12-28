package com.example.demo.loader;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class StudentLoader {

    final StudentRepository studentRepository;

@PostConstruct
    public void dataInitStudent(){
    Student student1=Student.builder()
            .firstName("Zahit")
            .lastName("Kaya")
            .email("a@a.com")
            .build();

    Student student2=Student.builder()
            .firstName("Mehmet")
            .lastName("Pınar")
            .email("b@b.com")
            .build();

    studentRepository.save(student1);
    studentRepository.save(student2);
}
}
