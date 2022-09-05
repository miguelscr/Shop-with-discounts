package com.tcs.ProductService.repositories;

import com.tcs.ProductService.models.ProductModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel,Long> {
    /*@Query("SELECT p.unit_price FROM product p WHERE id=:id")
    public abstract Optional<ProductModel> getProductPrice(@Param("id") Long id);*/
}