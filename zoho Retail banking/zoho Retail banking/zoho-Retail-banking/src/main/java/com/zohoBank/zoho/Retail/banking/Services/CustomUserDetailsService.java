package com.zohoBank.zoho.Retail.banking.Services;

import com.zohoBank.zoho.Retail.banking.models.User;
import com.zohoBank.zoho.Retail.banking.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }


    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        User user = userRepository.findUserByuId(uid)
                .orElseThrow(() -> new UsernameNotFoundException("User Name not Found"));
        Set<GrantedAuthority> authoritySet = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
//        System.out.println(authoritySet );
//        System.out.println(user);
        return new org.springframework.security.core.userdetails.User(user.getUId(), user.getPassword(), authoritySet);

    }

}
