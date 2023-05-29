package com.zohoBank.zoho.Retail.banking.controllers;

import com.zohoBank.zoho.Retail.banking.DTOs.DataAccessDTO.*;
import com.zohoBank.zoho.Retail.banking.Services.UserServices;
import com.zohoBank.zoho.Retail.banking.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class Profiler {


    private final UserRepository userRepository;
    private final UserServices services;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/UserDB")
    public ResponseEntity<List<UserDTO>> hello(){
        return ResponseEntity.ok(services.getUsers());
    }

//    @PreAuthorize("hasAnyRole('ADMIN' , 'USER')")
    @GetMapping("/Profile")
    public ResponseEntity<Profile> profile(){
        return ResponseEntity.ok(services.getInfo());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/TransactionList")
    public ResponseEntity<List<TransactionDBResponse>> transactionList(){

        return ResponseEntity.ok(services.getTransactions());
    }

    @GetMapping("/getTransactions")
    public ResponseEntity<List<TransactionDBResponse>> getTransactions(){
        return ResponseEntity.ok(services.getUserTransactions());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/UserCount")
    public ResponseEntity<Map<String, Integer>> userCount()
    {
        return ResponseEntity.ok(services.getUserJoinedCount());
    }


    @GetMapping("/UserTransactionCount")
    public ResponseEntity<Map<String, Integer>> userTransactionCount()
    {
        return ResponseEntity.ok(services.getUserTransactionsCount());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/TransactionCount")
    public ResponseEntity<Map<String, Integer>> TransactionCount()
    {
        return ResponseEntity.ok(services.getTransactionsCount());
    }

    @PostMapping("/ChangePassword")
    public Map<String,String> ChangePassword(@RequestBody PasswordRequest request) {
        Map<java.lang.String, java.lang.String> map = new HashMap<>();
        map.put("Status", services.changePassword(request));
        return map;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/GetTopFiveUsers")
    public ResponseEntity<List<TopCustomerDTO>> getTopFiveCustomers()
    {
        return ResponseEntity.ok(services.getTopFiveUsers());
    }
}
