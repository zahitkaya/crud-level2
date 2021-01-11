package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.model.request.StudentRequestDto;
import com.example.demo.model.response.GenericPagedDto;
import com.example.demo.model.response.StudentResponseDto;
import com.example.demo.service.StudentServiceImpl;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
@Api(tags = "Student", description = "Student Service")
public class StudentController {
    final StudentServiceImpl studentService;
    /*
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Students Selected"),
            @ApiResponse(code = 404, message = "Students Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Get all students as list")
    @GetMapping("school/v1/students")
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("school/v1/students")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Person created"),
            @ApiResponse(code = 409, message = "Student Conflict"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation("Add a new Student")
    public StudentResponseDto addStudent(@RequestBody @Valid StudentRequestDto student) {
        return studentService.addStudent(student);
    }

    @PutMapping("school/v1/students/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student updated"),
            @ApiResponse(code = 404, message = "Student Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @ApiOperation("Update a Student")
    public StudentResponseDto updateStudent(@PathVariable Integer id, @RequestBody @Valid StudentRequestDto student) {
        return studentService.updateStudentById(id, student);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Student Deleted"),
            @ApiResponse(code = 404, message = "Student Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping("school/v1/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a Student")
    public void deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student Selected"),
            @ApiResponse(code = 404, message = "Student Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @GetMapping("school/v1/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get a student from ID")
    public StudentResponseDto getStudentBody(@PathVariable @Valid Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("")
    @ApiOperation("Redirecting to Api URL")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Redirected"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    public void redirectToApiUrl(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui/#/Student");
    }

    @ApiResponses(value = {@ApiResponse(code = 200, message = "Students Listed"), @ApiResponse(code = 404, message = "Page not exist"), @ApiResponse(code = 500, message = "Internal Server Error"), @ApiResponse(code = 200, message = "Students Selected"), @ApiResponse(code = 404, message = "Students Not Found"), @ApiResponse(code = 500, message = "Internal Server Error")})
    @ApiOperation("Get all students as list")
    @GetMapping("school/v1/students")
    @ResponseStatus(HttpStatus.OK)
    public GenericPagedDto<StudentResponseDto> listStudents(Pageable page) {
        return studentService.listInvoice(page);
    }



}
