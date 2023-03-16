package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTO.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookRequestDto bookRequestDto){

        //I want to get the AuthorEntity..how to?

        int authorId = bookRequestDto.getAuthorId();   //int authorId = book.getAuthor().getId(); before DTO

        //Now I will be fetching authorEntity

        Author author = authorRepository.findById(authorId).get();


        //CONVERTOR
        //We have created Book entity so that we can save it into the db
        Book book = new Book();
        //Basic attributes are being set from DTO layer to Entity layer
        book.setGenre(bookRequestDto.getGenre());
        book.setIssued(false);
        book.setName(bookRequestDto.getName());
        book.setPages(bookRequestDto.getPages());


        //Do exception handling here

        //Basic attributes were set from postman before creating DTO

        //Setting the foreign key attribute in the child class
        book.setAuthor(author);

        //We need to update the list of books written by the author (parent)

        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);

        //author.setBooksWritten(currentBooksWritten);

        //Now the book is to be saved, but also author is to be saved
        // why to save/updating author? ---> because author entity has been updated...we need to resave/update it

        authorRepository.save(author); //data was modified

        //.save works as save as well as update function :--> if primary key is already present -> update it...if not present -> new object creation


        //bookRepository.save is not required cuz book will be auto saved by cascading effect..if done..it'll create duplicate book entry

        return "Book added successfully";





    }

}
