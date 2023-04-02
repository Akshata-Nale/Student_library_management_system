package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTO.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws Exception{

        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        //Get the book entity and card entity to set the transactions attributes(2 rules)

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();

        //Final aim : make a transaction Entity -> Set its attributes -> Save

        Transactions transactions = new Transactions();

        //Setting attributes
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssueOperation(true);

        transactions.setTransactionStatus(TransactionStatus.PENDING);
        //Attribute left : Success/Failure
        //Checking validations
        if(book==null || book.isIssued()==true){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book is not available");

        }

        if(card==null || (card.getCardStatus() != CardStatus.ACTIVATED)){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Card is not valid");
        }

        //We have reached a success case now
        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        //Set attributes of BOOK
        book.setIssued(true);
        //Between BOOK and TRANSACTIONS : Bidirectional
        List<Transactions> listOfTransactionForBook = book.getTransactionsList();
        listOfTransactionForBook.add(transactions);
        book.setTransactionsList(listOfTransactionForBook);

        //Changes in the card
        //BOOK and CARD
        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);

        //CARD and TRANSACTION : Bidirectional(in the parent class)
        List<Transactions> transactionsListForCard = card.getTransactionsList();
        transactionsListForCard.add(transactions);
        card.setTransactionsList(transactionsListForCard);


        //save the parent object
        cardRepository.save(card);
        //automatically by cascading book and transaction will be saved

        return "Book issued successfully";
    }

    public String getTransaction(int bookId, int cardId){
        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);

        String transactionId = transactionsList.get(0).getTransactionId();

        return transactionId;

    }



}
