package com.tcs.ProductService.controller;

import com.tcs.ProductService.models.ProductModel;
import com.tcs.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/post")
    public ResponseEntity<ProductModel> insertProduct(@RequestBody ProductModel product){
        return status(HttpStatus.OK).body(productService.saveProduct(product));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/getProductById/{id}")
    public Optional<ProductModel> getProductById(@PathVariable Long id){
        return this.productService.getProductByCode(id);
    }

    @GetMapping("/getDiscount")
    public Optional<Integer> getDiscount(@PathParam("productId") Long productId, @PathParam("couponId") Long couponId){

        return this.productService.getDiscount(productId,couponId);
    }

}
