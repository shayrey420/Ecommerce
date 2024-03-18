package com.example.demo.dao;

import com.example.demo.enteties.Buyers;
import com.example.demo.enteties.BuyersPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BuyersrReprository extends JpaRepository<Buyers, BuyersPk> {
    @Query("select u from Buyers u where u.user_name = :username")
    public Buyers getBuyersByUser_name(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("update Buyers c set  c.shipping_adress= :adress,c.phone_number= :phone where c.id= :id")
    public void updatbuyer(@Param("adress")String adress,@Param("phone") String phone,@Param("id")Integer id);
}
