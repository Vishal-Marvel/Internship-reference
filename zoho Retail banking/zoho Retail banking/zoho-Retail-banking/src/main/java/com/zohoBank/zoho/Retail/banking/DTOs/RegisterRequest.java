package com.zohoBank.zoho.Retail.banking.DTOs;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "username should not be empty")
    private String name;
    @NotBlank(message = "password should not be empty")
    private String password;
    @NotBlank(message = "homeless are not allowed try orphanage")
    private String address;
    @Email(message = "provide valid Email id")
    private String email;
    @Pattern(regexp = "^\\d{10}$")
    private String mobile;
    @NotNull(message = "Provide a valid date")
    private Date dateOfBirth;
    @NotBlank(message = "provide a valid aadar number")
    private String aadarno;
    @NotBlank(message = "provide a valid pan number")
    private String panno;
//    @Enumerated(EnumType.STRING)
//    private roles role;
}
