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
@IdClass(SellerPk.class)
public class Seller {
    @Id
    @Column(name = "Seller_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @Id
    private String userName;
    private String first_name;
    @NotNull
    private  String last_name;
    @NotNull
    private int phone;
    @NotNull
    private  String email;
    @NotNull
    private String password;
    @NotNull
    private  String Adress;
    @NotNull
    private  String Role;
   /* @JsonIgnore
    @ManyToMany(mappedBy = "sItem")
    private List<Item> SellersItem;*/
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "seller")
    private List<SellerItem> sellerItems;

    @OneToOne
    private Buyers buyers;

    public Buyers getBuyers() {
        return buyers;
    }

    public void setBuyers(Buyers buyers) {
        this.buyers = buyers;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    /*public List<Item> getSellersItem() {
        return SellersItem;
    }

    public void setSellersItem(List<Item> sellersItem) {
        SellersItem = sellersItem;
    }*/

    public List<SellerItem> getSellerItems() {
        return sellerItems;
    }

    public void setSellerItems(List<SellerItem> sellerItems) {
        this.sellerItems = sellerItems;
    }
}
