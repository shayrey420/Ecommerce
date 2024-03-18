package com.example.demo.dao;

import com.example.demo.enteties.Item;
import com.example.demo.enteties.Payment;
import com.example.demo.enteties.Shopping_cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingcartRepo extends JpaRepository<Shopping_cart,Integer> {
    @Query("from Shopping_cart as u where u.buyers.id= :id")
    public List<Shopping_cart> findAllItem(@Param("id") Integer id);


}
