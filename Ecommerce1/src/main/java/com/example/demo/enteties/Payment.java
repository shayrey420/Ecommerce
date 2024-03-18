package com.example.demo.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PaymentPk.class)
public class Payment {
    @Id
    @Column(name="Payment_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int oreder_id;

    private String card_name;

    private String card_number;

    private String card_company;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String card_expiratoion;
    @NotNull
    private String currentAdress;
    @NotNull
    private String permanentAdress;
    @NotNull
    private String division;
    @NotNull
    private String upazila;
    @NotNull
    private int zipCode;

    @OneToOne(cascade = CascadeType.ALL)
    private Discount discount;
    @OneToOne(cascade = CascadeType.ALL)
    private Review review;
    @OneToOne(cascade = CascadeType.ALL)
    private Shopping_cart shopping_cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOreder_id() {
        return oreder_id;
    }

    public void setOreder_id(int oreder_id) {
        this.oreder_id = oreder_id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_company() {
        return card_company;
    }

    public void setCard_company(String card_company) {
        this.card_company = card_company;
    }

    public String getCard_expiratoion() {
        return card_expiratoion;
    }

    public void setCard_expiratoion(String card_expiratoion) {
        this.card_expiratoion = card_expiratoion;
    }

    public String getCurrentAdress() {
        return currentAdress;
    }

    public void setCurrentAdress(String currentAdress) {
        this.currentAdress = currentAdress;
    }

    public String getPermanentAdress() {
        return permanentAdress;
    }

    public void setPermanentAdress(String permanentAdress) {
        this.permanentAdress = permanentAdress;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getUpazila() {
        return upazila;
    }

    public void setUpazila(String upazila) {
        this.upazila = upazila;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Shopping_cart getShopping_cart() {
        return shopping_cart;
    }

    public void setShopping_cart(Shopping_cart shopping_cart) {
        this.shopping_cart = shopping_cart;
    }
}
