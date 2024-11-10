package com.example.freshly_groceryapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CustomerDao {

    // Insert a new customer
    @Insert
    void insertCustomer(Customer customer);

    // Update an existing customer
    @Update
    void updateCustomer(Customer customer);

    // Delete a customer
    @Delete
    void deleteCustomer(Customer customer);

    // Get a customer by email
    @Query("SELECT * FROM Customer WHERE email = :email")
    Customer getCustomerByEmail(String email);

    // Get all customers
    @Query("SELECT * FROM Customer")
    List<Customer> getAllCustomers();

    // Delete all customers
    @Query("DELETE FROM Customer")
    void deleteAllCustomers();

    @Query("DELETE FROM CUSTOMER Where email = :c_email")
    void deleteCustomerbyEmail(int c_email);
}
