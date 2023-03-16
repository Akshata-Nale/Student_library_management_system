package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTO.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto){
        return "";
    }


}
