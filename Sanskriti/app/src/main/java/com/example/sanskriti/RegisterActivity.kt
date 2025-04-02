package com.example.sanskriti

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sanskriti.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize all fields to empty state
        binding.usernameField.setText("")
        binding.emailField.setText("")
        binding.passwordField.setText("")
        binding.confirmPasswordField.setText("")
        binding.termsCheckbox.isChecked = false

        // Clear any existing errors
        clearAllErrors()

        // Set up click listeners
        binding.registerButton.setOnClickListener {
            binding.registerButton.isEnabled = false
            try {
                if (validateForm()) {
                    registerUser()
                }
            } finally {
                binding.registerButton.isEnabled = true
            }
        }

        binding.profileImage.setOnClickListener {
            // TODO: Implement profile picture selection
            Toast.makeText(this, "Profile picture selection will be implemented", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearAllErrors() {
        binding.usernameField.error = null
        binding.emailField.error = null
        binding.passwordField.error = null
        binding.confirmPasswordField.error = null
    }

    private fun validateForm(): Boolean {
        clearAllErrors()
        var isValid = true
        var hasErrors = false

        // Username validation
        if (binding.usernameField.text.isNullOrEmpty()) {
            binding.usernameField.error = getString(R.string.required_field)
            isValid = false
            hasErrors = true
        }

        // Email validation (optional)
        binding.emailField.text?.let { email ->
            if (email.isNotBlank() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailField.error = getString(R.string.invalid_email)
                isValid = false
                hasErrors = true
            }
        }

        // Password validation
        if (binding.passwordField.text.isNullOrEmpty()) {
            binding.passwordField.error = getString(R.string.required_field)
            isValid = false
            hasErrors = true
        } else if (binding.passwordField.text!!.length < 6) {
            binding.passwordField.error = getString(R.string.password_length)
            isValid = false
            hasErrors = true
        }

        // Confirm password validation
        if (binding.confirmPasswordField.text.toString() != binding.passwordField.text.toString()) {
            binding.confirmPasswordField.error = getString(R.string.password_mismatch)
            isValid = false
            hasErrors = true
        }

        // Terms validation
        if (!binding.termsCheckbox.isChecked) {
            if (hasErrors) {
                // Only show snackbar if no other errors are visible
                Snackbar.make(binding.root, "You must agree to terms and conditions", Snackbar.LENGTH_SHORT).show()
            }
            isValid = false
        }

        return isValid
    }

    private fun registerUser() {
        // TODO: Implement actual registration logic
        // For now just show success and go back to login
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}