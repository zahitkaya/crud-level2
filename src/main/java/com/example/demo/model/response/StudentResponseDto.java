package com.example.demo.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.jfr.Timestamp;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ApiModel(value = "Student Response")
public class StudentResponseDto extends RepresentationModel<StudentResponseDto> {

    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "sequence_example")
    @SequenceGenerator(name = "sequence_example",initialValue = 100, allocationSize = 100)
    @Id
    @ApiModelProperty(value = "Unique id field of student object", example="100",required = true,position = 1)
    Integer id;

    @ApiModelProperty(value = "Firstname field of student object",position = 2)
    @NotBlank(message = "First name must not be blank")
    String firstName;

    @ApiModelProperty(value = "Lastname field of student object",position = 3)
    @NotBlank(message = "Last name must not be blank")
    String lastName;

    @ApiModelProperty(value = "Email field of student object",example = "detay@hot.com",position = 4)
    @Email(message = "Must be email format")
    @NotBlank
    String email;

    @Min(value = 18,message = "Student must be greater than 18")
    @Max(50)
    @Positive
    @ApiModelProperty(value = "Age field of student object",position = 5)
    Integer age;


    @PastOrPresent(message = "You can't enter future time.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Registiration Date field of student object",position = 6)
    Date signInDate;


    @Future(message = "You need to enter future date.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    @ApiModelProperty(value = "Graduate Year field of student object",position = 7)
    @CreatedDate
    Date yearOfGraduation;


}
