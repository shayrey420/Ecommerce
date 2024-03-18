package com.example.demo.enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(BuyersPk.class)
public class Buyers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="buyers_ID")
    private int id;
    @Id
    private String user_name;
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @NotNull
    private String phone_number;
    @NotNull
    private  String email;
    @NotNull
    private  String password;
    @NotNull
    private String shipping_adress;
    @NotNull
    private String shipping_city;
    @NotNull
    private  String shipping_division;
    @NotNull
    private String shipping_upazila;
    @NotNull
    private String shipping_zipcode;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    @NotNull
    private String Role;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "buyers")
    private List<SellerItem> sellerItems;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "buyers")
    private List<Review> reviews;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "buyers")
    private List<Shopping_cart> shopping_carts;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShipping_adress() {
        return shipping_adress;
    }

    public void setShipping_adress(String shipping_adress) {
        this.shipping_adress = shipping_adress;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public String getShipping_division() {
        return shipping_division;
    }

    public void setShipping_division(String shipping_division) {
        this.shipping_division = shipping_division;
    }

    public String getShipping_upazila() {
        return shipping_upazila;
    }

    public void setShipping_upazila(String shipping_upazila) {
        this.shipping_upazila = shipping_upazila;
    }

    public String getShipping_zipcode() {
        return shipping_zipcode;
    }

    public void setShipping_zipcode(String shipping_zipcode) {
        this.shipping_zipcode = shipping_zipcode;
    }

    public List<SellerItem> getSellerItems() {
        return sellerItems;
    }

    public void setSellerItems(List<SellerItem> sellerItems) {
        this.sellerItems = sellerItems;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Shopping_cart> getShopping_carts() {
        return shopping_carts;
    }

    public void setShopping_carts(List<Shopping_cart> shopping_carts) {
        this.shopping_carts = shopping_carts;
    }
}
