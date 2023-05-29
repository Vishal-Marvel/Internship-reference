package com.zohoBank.zoho.Retail.banking.repos;

import com.zohoBank.zoho.Retail.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

//    LocalDateTime thursday = LocalDateTime.now().minusDays(2);
    List<User> findAllByDateCreatedBetween(Date startDate, Date endDate);

    Optional<User> findUserByuId(String uid);
    Optional<User> findUserByAccNo(String accno);


    List<User> findTop5ByOrderByBalanceDesc();
}
