package com.example.foododeringsystem

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class SignInActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnSignIn: Button
    private lateinit var tvSignUp: TextView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)

        etEmail = findViewById(R.id.email)
        etPassword = findViewById(R.id.password)
        btnSignIn = findViewById(R.id.signInButton)
        tvSignUp = findViewById(R.id.tvSignUp)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val inputEmail = etEmail.text.toString().trim()
        val inputPassword = etPassword.text.toString().trim()

        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)

        if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
            return
        }

        if (savedEmail == null || savedPassword == null) {
            Toast.makeText(this, "No account found. Please sign up first", Toast.LENGTH_SHORT).show()
            return
        }

        if (inputEmail == savedEmail && inputPassword == savedPassword) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

            // 这里以后可以跳去 HomePage
            // val intent = Intent(this, HomeActivity::class.java)
            // startActivity(intent)
            // finish()

        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }
}