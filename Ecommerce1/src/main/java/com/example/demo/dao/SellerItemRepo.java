package com.example.demo.dao;

import com.example.demo.enteties.SellerItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerItemRepo extends JpaRepository<SellerItem,Integer> {
}
