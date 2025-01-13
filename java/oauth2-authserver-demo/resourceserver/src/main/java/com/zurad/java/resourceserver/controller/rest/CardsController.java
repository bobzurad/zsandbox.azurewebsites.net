package com.zurad.java.resourceserver.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import com.zurad.java.resourceserver.model.entity.Cards;
import com.zurad.java.resourceserver.model.entity.Customer;
import com.zurad.java.resourceserver.repository.CardsRepository;
import com.zurad.java.resourceserver.repository.CustomerRepository;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);

        return optionalCustomer.map(customer ->
            cardsRepository.findByCustomerId(customer.getId())).orElse(null);
    }

}
