package com.example.freshly_groceryapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface CategoryDao {

    // Insert a new category
    @Insert
    void insertCategory(Category category);

    // Update an existing category
    @Update
    void updateCategory(Category category);

    // Delete a specific category
    @Delete
    void deleteCategory(Category category);

    // Retrieve all categories
    @Query("SELECT * FROM category")
    List<Category> getAllCategories();

    // Retrieve a category by its ID
    @Query("SELECT * FROM category WHERE c_id = :categoryId")
    Category getCategoryById(int categoryId);
}

