package com.tcs.ProductService.entity;

public class Item {
    Coupon coupon;

    public Item(){}

    public Item(Coupon coupon) {
        this.coupon = coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public int getDiscount(){
        return coupon.getDiscount();
    }
}
