package com.zurad.java.resourceserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zurad.java.resourceserver.model.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {

}
