package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTO.AuthorEntryDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    public String createAuthor(AuthorEntryDto authorEntryDto){

        //IMP : in the params : object of type DTO is passed
        //But the Repository interacts only with the entities

        //Solution : Converting authorEntryDto --> Author

        //Created object of type Author
        Author author = new Author();

        //Setting its attributes so that we can save it in DB
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        authorRepository.save(author);

        return "Author added successfully";
    }

}
