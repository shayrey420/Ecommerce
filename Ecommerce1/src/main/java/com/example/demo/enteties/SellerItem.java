package com.example.demo.enteties;

import javax.persistence.*;

@Entity
@Table(name = "item_has_seller")
public class SellerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Seller seller;
    @ManyToOne(cascade = CascadeType.ALL)
    private Item item;
   /* @OneToOne(cascade = CascadeType.ALL)
    private Admin admin;*/
    @ManyToOne
    private Buyers buyers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    /*public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }*/

    public Buyers getBuyers() {
        return buyers;
    }

    public void setBuyers(Buyers buyers) {
        this.buyers = buyers;
    }
}
