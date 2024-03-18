package com.example.demo.enteties;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Item {
    @Id
    @Column(name = "Item_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Item_name;
    private String Item_Brand;
    private String Item_type;
    private  int quantity;
    private double unit_price;
    private String seller_name;
    private String description;
    private String category;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /*@ManyToMany
        @JoinTable(
                name = "item_has_seller"

        )
        private List<Seller> sItem;*/
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "item")
    private List<SellerItem> sellerItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public String getItem_Brand() {
        return Item_Brand;
    }

    public void setItem_Brand(String item_Brand) {
        Item_Brand = item_Brand;
    }

    public String getItem_type() {
        return Item_type;
    }

    public void setItem_type(String item_type) {
        Item_type = item_type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SellerItem> getSellerItems() {
        return sellerItems;
    }

    public void setSellerItems(List<SellerItem> sellerItems) {
        this.sellerItems = sellerItems;
    }

}
