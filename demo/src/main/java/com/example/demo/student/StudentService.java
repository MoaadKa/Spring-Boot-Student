package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepositiry studentRepositiry;

    @Autowired
    public StudentService(StudentRepositiry studentRepositiry) {
        this.studentRepositiry = studentRepositiry;
    }

    public List<Student> getStudents() {
        return studentRepositiry.findAll();
    }

    public void addStudent(Student student) {
     Optional<Student> studentByEmail = studentRepositiry
             .findStudentByEmail(student.getEmail());
     if (studentByEmail.isPresent()){
         throw new IllegalStateException("Email already taken");
     }
     studentRepositiry.save(student);
    }

    public void deleteStudent(Long studentId) {
      boolean exists = studentRepositiry.existsById(studentId);
      if(!exists){
          throw new IllegalStateException("student with id "+studentId+" doesn't exist");
      }
        studentRepositiry.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email
                              ){
       Student student =  studentRepositiry.findById(studentId)
               .orElseThrow(() ->  new IllegalStateException("Student with ID :"+studentId+" not found"));
       if (name!= null && name.length()>0 && !Objects.equals(name,student.getName())){
           student.setName(name);
       }
       if (email!= null && email.length()>0 && !Objects.equals(email,student.getEmail())){
           Optional<Student> optionalStudent = studentRepositiry.findStudentByEmail(email);
           if (optionalStudent.isPresent()){
               throw new IllegalStateException("Email already exixts");
           }
           student.setEmail(email);
       }
    }
}
