package com.springdemo.rest;

import com.springdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRESTController
{
    private List<Student> students;

    @PostConstruct
    public void loadData()
    {
        students = new ArrayList<>();

        students.add(new Student("Patrik", "Gugh"));
        students.add(new Student("Balazs", "Nagy"));
        students.add(new Student("Zoli", "Meszaros"));
        students.add(new Student("Laci", "Radvanyi"));
    }


    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id)
    {
        if (id >= students.size() || id < 0)
        {
            throw new StudentNotFoundException("Student id not found: " + id);
        }

        return students.get(id);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception)
    {
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();

        studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(exception.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
    }
}
