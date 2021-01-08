package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "Student")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "sequence_example")
    @SequenceGenerator(name = "sequence_example",initialValue = 100, allocationSize = 100)
    @Id
    Integer id;

    @Column(nullable = false,length = 50)
    String firstName;
    @Column(nullable = false,length = 50)
    String lastName;
    @Column(nullable = false,length = 50)
    String email;
    @Column(nullable = false,length = 50)
    Integer age;

    Date signInDate;

    Date graduateYear;

}
