package com.example.foododeringsystem

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnTogglePassword: ImageButton
    private lateinit var btnToggleConfirmPassword: ImageButton

    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnTogglePassword = findViewById(R.id.btnTogglePassword)
        btnToggleConfirmPassword = findViewById(R.id.btnToggleConfirmPassword)

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        btnTogglePassword.setOnClickListener {
            togglePassword()
        }

        btnToggleConfirmPassword.setOnClickListener {
            toggleConfirmPassword()
        }
    }

    private fun signUpUser() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        when {
            email.isEmpty() -> {
                etEmail.error = "Email is required"
                etEmail.requestFocus()
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                etEmail.error = "Enter a valid email"
                etEmail.requestFocus()
            }

            password.isEmpty() -> {
                etPassword.error = "Password is required"
                etPassword.requestFocus()
            }

            password.length < 6 -> {
                etPassword.error = "Password must be at least 6 characters"
                etPassword.requestFocus()
            }

            confirmPassword.isEmpty() -> {
                etConfirmPassword.error = "Please confirm your password"
                etConfirmPassword.requestFocus()
            }

            password != confirmPassword -> {
                etConfirmPassword.error = "Passwords do not match"
                etConfirmPassword.requestFocus()
            }

            else -> {
                Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()

                // 如果你要跳去 Sign In page，就打开这个
                // val intent = Intent(this, SignInActivity::class.java)
                // startActivity(intent)
                // finish()
            }
        }
    }

    private fun togglePassword() {
        if (isPasswordVisible) {
            etPassword.inputType =
                android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            etPassword.inputType =
                android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        etPassword.setSelection(etPassword.text.length)
        isPasswordVisible = !isPasswordVisible
    }

    private fun toggleConfirmPassword() {
        if (isConfirmPasswordVisible) {
            etConfirmPassword.inputType =
                android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            etConfirmPassword.inputType =
                android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        etConfirmPassword.setSelection(etConfirmPassword.text.length)
        isConfirmPasswordVisible = !isConfirmPasswordVisible
    }
}