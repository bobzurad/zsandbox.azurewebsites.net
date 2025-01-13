package com.zurad.java.resourceserver.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.zurad.java.resourceserver.model.entity.Customer;
import com.zurad.java.resourceserver.model.entity.Loans;
import com.zurad.java.resourceserver.repository.CustomerRepository;
import com.zurad.java.resourceserver.repository.LoanRepository;

@RestController
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);

        return optionalCustomer.map(customer ->
                loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId())).orElse(null);
    }
}
