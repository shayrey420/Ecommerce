package com.example.demo.dao;

import com.example.demo.enteties.Payment;
import com.example.demo.enteties.PaymentPk;
import com.example.demo.enteties.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepo extends JpaRepository<Payment, PaymentPk> {
    @Query("select u from Payment u where u.shopping_cart.id= :id")
    public Payment getPaymentByID(@Param("id") Integer id);
    //insert into student (age, name, id) values (?, ?, ?)

}
