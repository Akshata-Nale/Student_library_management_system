package com.example.Student_Library_Management_System.Repositories;

import com.example.Student_Library_Management_System.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Integer> {

    @Query(value = "select * from transactions t where t.book_id=:bookId and t.card_id=:cardId and is_issue_operation=true",nativeQuery = true)
    List<Transactions> getTransactionsForBookAndCard(int bookId, int cardId);
    //whenever you want to refer to the parameters you use ":" and variable name should be same
}
