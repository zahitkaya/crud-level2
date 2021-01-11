package com.example.demo.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ApiModel(value = "Student Request",description = "Student's entity")

public class StudentRequestDto {

    @ApiModelProperty(value = "Firstname field of student object",position = 1)
    @NotBlank(message = "First name must not be blank")
    String firstName;

    @ApiModelProperty(value = "Lastname field of student object",position = 2)
    @NotBlank(message = "Last name must not be blank")
    String lastName;

    @ApiModelProperty(value = "Email field of student object",position = 3)
    @Email(message = "Must be email format")
    @NotBlank
    String email;

    @Min(value = 18,message = "Student must be greater than 18")
    @Max(50)
    @Positive
    @ApiModelProperty(value = "Age field of student object",position = 4)
    Integer age;

    @PastOrPresent(message = "You can't enter future time.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Registiration Date field of student object",position = 5,hidden = true)
    Date signInDate;

    @Future(message = "You need to enter future date.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    @ApiModelProperty(value = "Graduate Year field of student object",position = 6,hidden = true)
    Date graduateYear;


}
