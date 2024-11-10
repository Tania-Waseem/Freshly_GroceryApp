package com.example.freshly_groceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class CustomerProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_profile);

        Button editProfileBtn;
        Button deleteProfileBtn;

        editProfileBtn = findViewById(R.id.editProfile);
        deleteProfileBtn = findViewById(R.id.deleteAccount);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-database").build();
        CustomerDao customerDao = db.customerDao();


        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to be executed when the button is clicked
                Intent intent = new Intent(CustomerProfile.this, EditCustomerProfile.class);
                startActivity(intent);
            }
        });
        int customerIdToDelete = 1;

        deleteProfileBtn.setOnClickListener(view -> {
            new Thread(() -> {
                customerDao.deleteCustomerbyEmail(customerIdToDelete);
                runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Customer deleted", Toast.LENGTH_SHORT).show());
            }).start();
        });
    }
}