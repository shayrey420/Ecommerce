package com.example.demo.dao;

import com.example.demo.enteties.Seller;
import com.example.demo.enteties.SellerPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SellerReporository extends JpaRepository<Seller, SellerPk> {
    @Query("select u from Seller u where u.buyers.id= :id")
    public Seller getSellerById(@Param("id")Integer id);
    @Transactional
    @Modifying
    @Query("update Seller c set  c.Adress= :adress,c.phone= :phone where c.id= :id")
    public void updatSeller(@Param("adress")String adress,@Param("phone") Integer phone,@Param("id")Integer id);
}