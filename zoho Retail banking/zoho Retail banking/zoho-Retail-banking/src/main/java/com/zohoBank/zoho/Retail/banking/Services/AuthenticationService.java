package com.zohoBank.zoho.Retail.banking.Services;

import com.zohoBank.zoho.Retail.banking.DTOs.*;
import com.zohoBank.zoho.Retail.banking.DTOs.DataAccessDTO.UserDTO;
import com.zohoBank.zoho.Retail.banking.models.Role;
import com.zohoBank.zoho.Retail.banking.models.User;
import com.zohoBank.zoho.Retail.banking.repos.RoleRepo;
import com.zohoBank.zoho.Retail.banking.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepo;
    private final JWTService jwtService;
    private final UserServices userServices;
    private final AuthenticationManager authenticationManager;
    private final RoleRepo roleRepo;

    public RegisterResponse register(RegisterRequest request) {
//        var user = User.builder()
////                .uId(user.getUId())
//                .name(request.getName())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .address(request.getAddress())
//                .email(request.getEmail())
//                .mobile(request.getMobile())
//                .aadarno(passwordEncoder.encode(request.getAadarno()))
//                .panno(passwordEncoder.encode(request.getPanno()))
//                .dateOfBirth(request.getDateOfBirth())
//                .role(roles.USER)
//                .build();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDTO userDTO =new UserDTO();
        userDTO.setName(request.getName());
        userDTO.setAddress(request.getAddress());
        userDTO.setPassword(passwordEncoder.encode(request.getPassword()));
        userDTO.setEmail(request.getEmail());
        userDTO.setMobile(request.getMobile());
        userDTO.setAadarno(request.getAadarno());
        userDTO.setDateOfBirth(request.getDateOfBirth());
        userDTO.setPanno(request.getPanno());
        Role role = roleRepo.findByName("ROLE_USER");
        User user = userServices.convertDTOToUser(userDTO);
        user.setRoles(Set.of(role));
        userRepo.save(user);
        return RegisterResponse.builder().uId(user.getUId()).build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
//        System.out.println("request = " + request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUId(),request.getPassword()
                )
        );

        var user =  userRepo.findUserByuId(request.getUId()).orElseThrow(()->new UsernameNotFoundException("user not exist"));

//        var user =  userRepo.findUserByuId(request.getUId());
        user.setLastLoggedin(new Date());
        var jwtToken = jwtService.generateToken(user);
        Iterator<Role> iterator = user.getRoles().iterator();
        Role firstElement = iterator.next();

        String role = firstElement.getName();
//        System.out.println(jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).role(role).build();

    }

    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Logout";
    }
}
