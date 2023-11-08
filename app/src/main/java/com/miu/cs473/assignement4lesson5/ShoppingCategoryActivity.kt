package com.miu.cs473.assignement4lesson5

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShoppingCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)

        val username = intent.getStringExtra("username")

        val welcomeText = findViewById<TextView>(R.id.welcome_text)
        welcomeText.text = "Welcome, $username"

        val electronicsImage = findViewById<ImageView>(R.id.electronicsImage)
        val clothingImage = findViewById<ImageView>(R.id.clothingImage)
        val beautyImage = findViewById<ImageView>(R.id.beautyImage)
        val booksImage = findViewById<ImageView>(R.id.foodImage)

        electronicsImage.setOnClickListener {
            displayCategoryToast("Electronics category")
        }

        clothingImage.setOnClickListener {
            displayCategoryToast("Clothing category")
        }

        beautyImage.setOnClickListener {
            displayCategoryToast("Beauty category")
        }

        booksImage.setOnClickListener {
            displayCategoryToast("Books category")
        }
    }

    private fun displayCategoryToast(message: String) {
        Toast.makeText(this, "You have chosen the $message", Toast.LENGTH_SHORT).show()
    }
}