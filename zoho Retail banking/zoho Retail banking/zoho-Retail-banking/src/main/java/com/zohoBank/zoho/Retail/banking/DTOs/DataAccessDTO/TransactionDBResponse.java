package com.zohoBank.zoho.Retail.banking.DTOs.DataAccessDTO;

import com.zohoBank.zoho.Retail.banking.models.Transactions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDBResponse {

    private String payerId;
    private Double amount;
    private String message;
    private String status;
    private String tId;
    private Date createddate;
    private Double balance;
}
