package com.example.freshly_groceryapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VendorDao {

    // Insert a new vendor
    @Insert
    void insertVendor(Vendor vendor);

    // Update an existing vendor
    @Update
    void updateVendor(Vendor vendor);

    // Delete a vendor
    @Delete
    void deleteVendor(Vendor vendor);

    // Get a vendor by username
    @Query("SELECT * FROM Vendor WHERE username = :username")
    Vendor getVendorByUsername(String username);

    // Get all vendors
    @Query("SELECT * FROM Vendor")
    List<Vendor> getAllVendors();

    // Delete all vendors
    @Query("DELETE FROM Vendor")
    void deleteAllVendors();
}
