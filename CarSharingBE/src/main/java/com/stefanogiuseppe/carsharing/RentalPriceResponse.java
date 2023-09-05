package com.stefanogiuseppe.carsharing;

public class RentalPriceResponse {
    private double price;
    private boolean extraPay;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isExtraPay() {
        return extraPay;
    }

    public void setExtraPay(boolean extraPay) {
        this.extraPay = extraPay;
    }

    public RentalPriceResponse(double price, boolean extraPay) {
        this.price = price;
        this.extraPay = extraPay;
    }
}
