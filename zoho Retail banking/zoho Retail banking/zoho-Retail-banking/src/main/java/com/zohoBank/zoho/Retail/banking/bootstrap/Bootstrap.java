package com.zohoBank.zoho.Retail.banking.bootstrap;

import com.zohoBank.zoho.Retail.banking.models.Role;
import com.zohoBank.zoho.Retail.banking.models.Transactions;
import com.zohoBank.zoho.Retail.banking.models.User;
import com.zohoBank.zoho.Retail.banking.repos.TransactionsRepository;
import com.zohoBank.zoho.Retail.banking.repos.UserRepository;
import com.zohoBank.zoho.Retail.banking.repos.RoleRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
//@Component
public class Bootstrap implements CommandLineRunner {


    private final UserRepository userRepo;
    private final RoleRepo roleRepo;
    private final TransactionsRepository transactionrepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Hello");

//implementing the role table
        Role user_role = new Role(1L, "ROLE_USER");
        Role admin = new Role (2L, "ROLE_ADMIN");

//Code for creating an admin, comment when u don't want an admin to be formed while run
        roleRepo.saveAll(List.of(user_role, admin));
//    Role admin = roleRepo.findByName("ROLE_ADMIN");
        User user = new User("admin",  passwordEncoder.encode("admin@123"),  "address",  "email",  "mobile", new Date(), new Date(), new Date(),  "aadarno",  "panno", Set.of(admin));
        userRepo.save(user);

       List<Transactions> transactions = new ArrayList<>();
       Transactions t= new Transactions();
        t.setPayeeId("456678");
        t.setAmount(4000.0);
        t.setUser(user);
        t.setMessage("Rs 4000.0 is sent to BINOZaPRO");
        t.setStatus("SUCCESS");
        t.setInstanceBalance(0.0);
        transactions.add(t);
        Transactions t1 =new Transactions();
        t1.setPayeeId("567890");
        t1.setAmount(3000.0);
        t1.setUser(user);
        t1.setMessage("Rs 3000.0 is sent to TreeGanesh");
        t1.setStatus("SUCCESS");
        t1.setInstanceBalance(0.0);
        transactions.add(t1);
        transactionrepo.saveAll(transactions);

//      uncomment the below after logging in
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("auth = " + auth.getPrincipal());

        //uncomment to view the usersDB
//            List<User> user = userRepo.findAll();
//        user.forEach(System.out::println);
//        System.exit(0);



//        System.out.println("HElloi");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH, -7);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        List<Date> dates = userRepo.findAll().stream().map(User::getDateCreated).map(dateFormat::format).map(date -> {
//            try {
//                return dateFormat.parse(date);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }).filter(date -> date.after(cal.getTime())).distinct().toList();

//        Date one_day = new Date(0,0,1);
//        for (Date date : dates){
//            cal.setTime(date);
//            cal.add(Calendar.DAY_OF_MONTH, 1);
//            System.out.println(userRepo.findAllByDateCreatedBetween(date, cal.getTime()).size());
//            System.out.println("date = " + date + "cal = " + cal.getTime());
//
//
//        }

//        System.out.println(transactionrepo.countTransactionsByDateCreated());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String dateString = dateFormat.format(new Date());
//        Date date = dateFormat.parse(dateString);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//
//        Calendar cal1 = Calendar.getInstance();
//        cal1.setTime(date);
//        cal1.add(Calendar.DAY_OF_MONTH,1);
//
//        for (int i = 0; i < 7; i++) {
//            int count = userRepo.findAllByDateCreatedBetween(cal.getTime(), cal1.getTime()).size();
//            System.out.println("count = " + count);
//            cal1.add(Calendar.DAY_OF_MONTH, -1);
//            cal.add(Calendar.DAY_OF_MONTH, -1);
//        }
    }
}
