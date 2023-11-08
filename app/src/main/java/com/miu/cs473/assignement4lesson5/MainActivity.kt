package com.miu.cs473.assignement4lesson5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val users = ArrayList<User>()
    private val REGISTER_REQUEST_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initiazeData()
        initiazeViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REGISTER_REQUEST_CODE && resultCode == RESULT_OK) {
            val newUser = data?.getSerializableExtra("newUser") as? User

            if (newUser != null) {
                users.add(newUser)
            }
        }
    }

    private fun initiazeViews() {
        val signInButton = findViewById<Button>(R.id.createAccBtn)
        signInButton.setOnClickListener { signIn() }

        val newUserButton = findViewById<Button>(R.id.createAccountBtn)
        newUserButton.setOnClickListener { lunchRegisterIntent() }

        val forgotPasswordButton = findViewById<TextView>(R.id.forgotPasswordTx)
        forgotPasswordButton.setOnClickListener { forgotPassword() }
    }

    private fun lunchRegisterIntent() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivityForResult(intent, REGISTER_REQUEST_CODE)
    }

    private fun initiazeData() {
        users.add(User("John", "Doe", "john@example.com", "password1"))
        users.add(User("Jane", "Smith", "jane@example.com", "password2"))
        users.add(User("Alice", "Johnson", "alice@example.com", "password3"))
        users.add(User("Bob", "Brown", "bob@example.com", "password4"))
        users.add(User("Eve", "Wilson", "eve@example.com", "password5"))
    }

    private fun signIn() {
        val emailEditText = findViewById<EditText>(R.id.signinEmailEt)
        val passwordEditText = findViewById<EditText>(R.id.passwordEt)

        val username = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val matchingUser = users.find { it.username == username && it.password == password }

        if (matchingUser != null) {
            val intent = Intent(this, ShoppingCategoryActivity::class.java)
            intent.putExtra("username", matchingUser.username)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun forgotPassword() {
        val emailEditText = findViewById<EditText>(R.id.signinEmailEt)
        val username = emailEditText.text.toString()

        val user = users.find { it.username == username }

        if (user != null) {
            // Retrieve the user's password
            val password = user.password

            // Use an implicit Intent to send the password via email
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(user.username))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Password Recovery")
            intent.putExtra(Intent.EXTRA_TEXT, "Your password is: $password")

            try {
                startActivity(Intent.createChooser(intent, "Send Email"))
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to send email", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
        }
    }
}