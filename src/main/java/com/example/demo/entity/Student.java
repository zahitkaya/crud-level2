package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "Student Api")
public class Student {
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "sequence_example")
    @SequenceGenerator(name = "sequence_example",initialValue = 100, allocationSize = 100)
    @Id
    @ApiModelProperty(value = "Unique id field of student object", example="100",required = true)
    Integer id;

    @ApiModelProperty(value = "Firstname field of student object")
    @NotBlank(message = "First name must not be blank")
    String firstName;

    @ApiModelProperty(value = "Lastname field of student object")
    @NotBlank(message = "Last name must not be blank")
    String lastName;

    @ApiModelProperty(value = "Email field of student object")
    @Email(message = "Must be email format")
    @NotBlank
    String email;

    @Min(value = 18,message = "Student must be greater than 18")
    @Max(50)
    @Positive
    @ApiModelProperty(value = "Age field of student object")
    Integer age;

    @PastOrPresent(message = "You can't enter future time.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Registiration Date field of student object")
    Date signInDate;

    @Future(message = "You need to enter future date.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    @ApiModelProperty(value = "Graduate Year field of student object")
    Date graduateYear;

}
