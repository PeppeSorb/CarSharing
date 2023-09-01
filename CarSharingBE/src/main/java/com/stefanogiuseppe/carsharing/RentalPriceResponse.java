package com.stefanogiuseppe.carsharing;

public class RentalPriceResponse {
    private double amount;
    private boolean extraPay;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isExtraPay() {
        return extraPay;
    }

    public void setExtraPay(boolean extraPay) {
        this.extraPay = extraPay;
    }

    public RentalPriceResponse(double amount, boolean extraPay) {
        this.amount = amount;
        this.extraPay = extraPay;
    }
}
