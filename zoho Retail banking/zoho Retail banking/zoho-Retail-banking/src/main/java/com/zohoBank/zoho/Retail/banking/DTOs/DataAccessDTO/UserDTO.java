package com.zohoBank.zoho.Retail.banking.DTOs.DataAccessDTO;

import com.zohoBank.zoho.Retail.banking.models.Role;
import com.zohoBank.zoho.Retail.banking.models.Transactions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String name;
    private String password;
    private String address;
    private String email;
    private String mobile;
    private Date dateOfBirth;
    private String aadarno;
    private String panno;
    private String uid;
    private String accNo;
//    private Set<Transactions> transactions;
}
