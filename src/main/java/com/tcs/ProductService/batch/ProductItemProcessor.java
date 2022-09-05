package com.tcs.ProductService.batch;

import com.tcs.ProductService.models.ProductModel;
import org.springframework.batch.item.ItemProcessor;

public class ProductItemProcessor implements ItemProcessor<ProductModel,ProductModel> {


    @Override
    public ProductModel process(ProductModel productModel) throws Exception {
        return productModel;
    }
}