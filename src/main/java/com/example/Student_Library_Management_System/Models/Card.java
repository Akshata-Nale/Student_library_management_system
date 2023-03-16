package com.example.Student_Library_Management_System.Models;


import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp  //will automatically stamp the time when entry will be created
    private Date createdOn; //imported from UTIL because it is a more detailed and it also has TIMESTAMP


    @UpdateTimestamp //Sets time when an entry is updated
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING) //tells SQL to keep it in String format...stores enum type as VARCHAR
    private CardStatus cardStatus;  //this variable is used in the parent class while doing the bidirectional mapping


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    //Card is child wrt Student
    @OneToOne
    @JoinColumn
    private Student student; //This variable used in parent class while doing bidirectional mapping

    //Card is parent wrt Book
    @OneToMany(mappedBy = "card", cascade =CascadeType.ALL)
    private List<Book> booksIssued;

    //Card is parent wrt Transactions
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transactions> transactionsList = new ArrayList<>();

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public Card() {
        booksIssued = new ArrayList<>(); // to avoid nullPointerException initializing the list by yourself
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }
}
