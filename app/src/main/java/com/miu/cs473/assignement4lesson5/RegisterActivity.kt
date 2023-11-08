package com.miu.cs473.assignement4lesson5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initiazeViews()
    }

    private fun initiazeViews() {
        val createAccountButton = findViewById<Button>(R.id.createAccBtn)
        createAccountButton.setOnClickListener { createAccount() }
    }

    private fun createAccount() {
        val firstNameEditText = findViewById<EditText>(R.id.firstNameEt)
        val lastNameEditText = findViewById<EditText>(R.id.lastNameEt)
        val emailEditText = findViewById<EditText>(R.id.emailEt)
        val passwordEditText = findViewById<EditText>(R.id.passwordEt)

        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val username = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val newUser = User(firstName, lastName, username, password)

        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()

        val intent = Intent()
        intent.putExtra("newUser", newUser)
        setResult(RESULT_OK, intent)
        finish()
    }
}