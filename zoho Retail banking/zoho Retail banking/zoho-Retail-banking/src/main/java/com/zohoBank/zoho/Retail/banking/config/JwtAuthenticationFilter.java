package com.zohoBank.zoho.Retail.banking.config;

import com.zohoBank.zoho.Retail.banking.ExceptionsForThisApplication.AccessDeniedException;
import com.zohoBank.zoho.Retail.banking.Services.CustomUserDetailsService;
import com.zohoBank.zoho.Retail.banking.Services.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService JwtService;
    private final CustomUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String _id;

            if(authHeader == null ||!authHeader.startsWith("Bearer"))
            {

                filterChain.doFilter(request,response);
                return;
            }
            jwt=authHeader.substring(7);
            _id= JwtService.extractID(jwt);
            System.out.println(_id);
            if(_id != null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(_id);
                if(JwtService.isTokenValid(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    System.out.println(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authToken);


                }
                filterChain.doFilter(request,response);
            }
    }
}
