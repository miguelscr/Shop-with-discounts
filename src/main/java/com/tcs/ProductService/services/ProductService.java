package com.tcs.ProductService.services;

import com.tcs.ProductService.entity.Coupon;
import com.tcs.ProductService.entity.Item;
import com.tcs.ProductService.entity.ItemService;
import com.tcs.ProductService.models.ProductModel;
import com.tcs.ProductService.repositories.ProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService implements ItemService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RestTemplate clientRest;
    private final Log LOGGER = LogFactory.getLog(ProductService.class);

    public ProductModel saveProduct(ProductModel product){
        ProductModel result = productRepository.save(product);
        LOGGER.info("Log result save: "+result.getId() + " - " + result.getName() + " - " + result.getUnitPrice());
        return result;
    }

    public List<ProductModel> getAllProducts(){
        List<ProductModel> result = (List<ProductModel>) productRepository.findAll();
        for (int i=0;i< result.size();i++)
            LOGGER.info("Log result getAll: "+result.get(i).getId() + " - " + result.get(i).getName() + " - " + result.get(i).getUnitPrice());
        return result;
    }

    public Optional<ProductModel> getProductByCode(Long id){
        return productRepository.findById(id);
    }

    public Optional<Integer> getDiscount(Long productId, Long couponId){
        Integer price = productRepository.findById(productId).get().getUnitPrice();
        Integer discount = getCouponById(couponId);
        Integer result = price - discount;
        LOGGER.info("Log result getDiscount: " + result);
        return Optional.of(result);
    }

    @Override
    public int getCouponById(Long id) {
        Map<String,Long> pathVariables = new HashMap<String,Long>();
        pathVariables.put("id",id);
        Coupon coupon = clientRest.getForObject("http://localhost:8082/api/getCoupon/{id}", Coupon.class,pathVariables);
        Item disc = new Item(coupon);
        return disc.getDiscount();
    }
}
