package com.amitph.spring.tutorials.students;

import com.amitph.spring.tutorials.students.entity.Student;
import com.amitph.spring.tutorials.students.entity.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentsController {
    private final StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @PostMapping("/students")
    public void postStudent(@RequestBody StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setYear(studentDto.getYear());
        studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public void putStudent(@PathVariable long id, @RequestBody StudentDto studentDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

        boolean updateRequired = false;

        if (studentDto.getFirstName() != null && studentDto.getFirstName().trim().length() > 0) {
            student.setFirstName(studentDto.getFirstName().trim());
            updateRequired = true;
        }

        if (studentDto.getLastName() != null && studentDto.getLastName().trim().length() > 0) {
            student.setFirstName(studentDto.getFirstName().trim());
            updateRequired = true;
        }

        if (studentDto.getYear() >= 0) {
            student.setYear(studentDto.getYear());
            updateRequired = true;
        }

        if (updateRequired = true) {
            studentRepository.save(student);
        }
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
    }
}