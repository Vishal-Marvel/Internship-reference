package com.zohoBank.zoho.Retail.banking.repos;

import com.zohoBank.zoho.Retail.banking.models.Transactions;
import com.zohoBank.zoho.Retail.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transactions,String> {
//    Optional<Transactions> findUserByuId(String uid);
    List<Transactions> findAllByUserOrderByDateCreatedDesc(User user);

    List<Transactions> findAllByUser(User user);
    List<Transactions> findAllByUserAndDateCreatedBetween(User user, Date start, Date end);
    List<Transactions> findAllByMessageIsLikeAndDateCreatedBetween(String string2, Date start, Date end);
    List<Transactions> findAllByDateCreatedBetween(Date start,Date end);
////    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.date = :date")
//    int countTransactionsByDateCreated(Date dateCreated);
}
