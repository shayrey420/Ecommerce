package com.example.demo.dao;

import com.example.demo.enteties.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface ItemRepo extends JpaRepository<Item, Integer> {
    @Query("from Item as u where u.seller_name = :username")
    public List<Item> findBySeller_name(@Param("username") String username);
    @Query("from Item as u")
    public List<Item> findAllItem();
    @Query("select u from  Item  u where u.id= :id")
    public Item findItemById(@Param("id") Integer id);
    @Query("from Item as u where u.category = :category")
    public List<Item> findItemByCategory(@Param("category") String category);

    @Transactional
    @Modifying
    @Query("update Item c set  c.unit_price= :price where c.id= :id")
    public void updateItem(@Param("id")Integer id,@Param("price") Double price);


}
