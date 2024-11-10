package com.example.freshly_groceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VendorHomePage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private EditText searchEditText;
    private ImageView profileImageView;
    private FloatingActionButton addProductFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home_page);

        // Initialize views
        recyclerView = findViewById(R.id.vendorProductRecyclerView);
        profileImageView = findViewById(R.id.profileImageView);
        addProductFAB = findViewById(R.id.addProductFAB);

        // Set up RecyclerView with GridLayoutManager for displaying products
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set layout manager programmatically
// 2 columns for grid
        productList = new ArrayList<>();
        //productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);


        productAdapter.notifyDataSetChanged();

        // Load the profile image using Glide (you can replace with dynamic URL)
        Glide.with(this)
                .load("https://example.com/profile_picture.jpg")
                .circleCrop() // For circular image
                .into(profileImageView);

        // Set a click listener on the Add Product button to navigate to the add product page
        addProductFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to AddProductActivity (you can create this Activity for adding new products)
                Intent intent = new Intent(VendorHomePage.this, UploadProductActivity.class);
                startActivity(intent);
            }
        });

        // Handle search input for filtering products (you can implement filtering logic)
        searchEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Filter products based on search query (you can implement the actual filtering)
                String query = charSequence.toString().toLowerCase();
                filterProducts(query);
            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {}
        });
    }

    // Method to filter products based on search query
    private void filterProducts(String query) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(query)) {
                filteredList.add(product);
            }
        }
    }
}