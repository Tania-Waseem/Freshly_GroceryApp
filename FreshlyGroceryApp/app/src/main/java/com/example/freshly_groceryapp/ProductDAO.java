package com.example.freshly_groceryapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM products WHERE category = :category")
    List<Product> getProductsByCategory(String category);

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();
}
