package com.example.Student_Library_Management_System.DTO;

public class IssueBookRequestDto {

    private int bookId;

    private int cardId;

    //studentId is not required as it is mapped with cardId


    public IssueBookRequestDto() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
