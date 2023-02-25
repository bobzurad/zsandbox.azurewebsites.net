package com.zurad.api.apidemo.repository;

import com.zurad.api.apidemo.entity.Product;
import org.springframework.data.repository.CrudRepository;  // use JpaRepository for pagination, sorting, or batch deletion
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository <Product, Long> { }