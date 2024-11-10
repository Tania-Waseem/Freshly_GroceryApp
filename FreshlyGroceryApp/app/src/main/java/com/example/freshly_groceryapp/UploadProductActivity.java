package com.example.freshly_groceryapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadProductActivity extends AppCompatActivity {
    private static final int REQUEST_GALLERY = 100;
    private static final int REQUEST_CAMERA = 101;
    private static final int REQUEST_CAMERA_PERMISSION = 102;

    private EditText editTextProductName, editTextProductDescription, editTextProductPrice, editTextProductCategory;
    private ImageView imageViewProduct;
    private Uri imageUri;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);

        db = AppDatabase.getInstance(this);

        editTextProductName = findViewById(R.id.productName);
        editTextProductDescription = findViewById(R.id.productDescription);
        editTextProductPrice = findViewById(R.id.productPrice);
        editTextProductCategory = findViewById(R.id.productCategory);

        Button buttonSelectImage = findViewById(R.id.uploadImageButton);
        Button buttonCaptureImage = findViewById(R.id.chooseFromGalleryButton);
        Button buttonUploadProduct = findViewById(R.id.addProductButton);

        buttonSelectImage.setOnClickListener(v -> selectImageFromGallery());
        buttonCaptureImage.setOnClickListener(v -> captureImageFromCamera());
        buttonUploadProduct.setOnClickListener(v -> uploadProduct());
    }

    private void selectImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    private void captureImageFromCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY && data != null) {
                imageUri = data.getData();
                imageViewProduct.setImageURI(imageUri);
            } else if (requestCode == REQUEST_CAMERA && data != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageViewProduct.setImageBitmap(photo);
                // Convert bitmap to Uri if needed
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureImageFromCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadProduct() {
        String name = editTextProductName.getText().toString();
        String description = editTextProductDescription.getText().toString();
        String priceStr = editTextProductPrice.getText().toString();
        String category = editTextProductCategory.getText().toString();

        if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty() || category.isEmpty() || imageUri == null) {
            Toast.makeText(this, "Please fill in all fields and select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product(name, description, price, category, imageUri.toString());
        new InsertProductTask().execute(product);
    }

    private class InsertProductTask extends AsyncTask<Product, Void, Void> {
        @Override
        protected Void doInBackground(Product... products) {
            db.productDao().insertProduct(products[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(UploadProductActivity.this, "Product uploaded", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
