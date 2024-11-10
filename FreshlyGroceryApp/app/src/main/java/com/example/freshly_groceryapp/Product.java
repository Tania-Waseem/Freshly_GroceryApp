package com.example.freshly_groceryapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "products",
        foreignKeys = {
                @ForeignKey(
                        entity = Vendor.class,
                        parentColumns = "id",
                        childColumns = "vendorId",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Category.class,
                        parentColumns = "c_id",
                        childColumns = "categoryId",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String category;
    private String imageUrl;
    private String description;
    private double price;
    private int vendorId;
    private int categoryId;

    public Product(String name, String category, String imageUrl, String description, double price, int vendorId, int categoryId) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.vendorId = vendorId;
        this.categoryId = categoryId;
    }

    // Getters and Setters

    public int getVendorId() { return vendorId;}
    public void setVendorId(int vendorId) { this.vendorId = vendorId;}

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId;}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description;}

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }


}
