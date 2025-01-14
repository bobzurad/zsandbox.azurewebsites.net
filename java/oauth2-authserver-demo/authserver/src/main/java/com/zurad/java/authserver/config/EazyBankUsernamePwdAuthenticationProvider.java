package com.zurad.java.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/*
    This AuthenticationProvider will be used when grant type authorization_code is used.
    This provider uses the EazyBankUserDetailsService to load the user from the database.
    After the user is loaded from the database, the PasswordEncoder is used to validate the password.
    If the password is valid, an Authentication token is returned.
    The PasswordEncoder used is defined as a @Bean in ProjectSecurityConfig
 */
@Component
public class EazyBankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;  // this will be an instance of EazyBankUserDetailsService

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(pwd, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, pwd, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid password!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
