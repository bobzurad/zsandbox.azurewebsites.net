package com.zurad.java.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.zurad.java.authserver.model.entity.Customer;
import com.zurad.java.authserver.repository.CustomerRepository;

/*
    This class is used by the AuthenticationProvider to load the user information from the database.
 */
@Service
public class EazyBankUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository
            .findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User details not found for the user: " + username));

        List<GrantedAuthority> authorities = customer
            .getAuthorities()
            .stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());

        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }
}