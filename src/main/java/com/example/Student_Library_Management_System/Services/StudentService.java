package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    //IMP : Always set the attributes before saving in DB

    public String createStudent(Student student){

        //Student from the postman is already having basic attributes set

        //Card should also be auto generated when student is created
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED); //Card status is being set
        card.setStudentVariableName(student); // Foreign key attribute

        //For student
        student.setCard(card);

        //if there was unidirectional mapping we have to save both of them
        //but here we are using bidirectional mapping so child will be automatically saved

        studentRepository.save(student);  //by cascading effect child will automatically be saved.

        return "Student and Card added";


    }

}
