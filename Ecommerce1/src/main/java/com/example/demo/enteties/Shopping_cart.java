package com.example.demo.enteties;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Shopping_cart {
    @Id
    @Column(name = "Order_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private double total_amount;
    @NotNull
    private int total_quantity;

    private String payment_date;
    @NotNull
    private String order_date;
    @NotNull
    private boolean cancel;

    private String shipping_Date;
    @ManyToOne
    private Buyers buyers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public String getShipping_Date() {
        return shipping_Date;
    }

    public void setShipping_Date(String shipping_Date) {
        this.shipping_Date = shipping_Date;
    }

    public Buyers getBuyers() {
        return buyers;
    }

    public void setBuyers(Buyers buyers) {
        this.buyers = buyers;
    }
}
