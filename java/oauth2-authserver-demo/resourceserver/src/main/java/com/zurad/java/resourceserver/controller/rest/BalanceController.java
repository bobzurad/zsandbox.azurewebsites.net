package com.zurad.java.resourceserver.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.zurad.java.resourceserver.model.entity.AccountTransactions;
import com.zurad.java.resourceserver.model.entity.Customer;
import com.zurad.java.resourceserver.repository.AccountTransactionsRepository;
import com.zurad.java.resourceserver.repository.CustomerRepository;

@RestController
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);

        return optionalCustomer.map(customer ->
            accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(customer.getId())).orElse(null);
    }
}
