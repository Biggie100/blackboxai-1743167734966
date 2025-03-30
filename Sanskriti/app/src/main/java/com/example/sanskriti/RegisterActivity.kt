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

        // Set up click listeners
        binding.registerButton.setOnClickListener {
            if (validateForm()) {
                registerUser()
            }
        }

        binding.profileImage.setOnClickListener {
            // TODO: Implement profile picture selection
            Toast.makeText(this, "Profile picture selection will be implemented", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        // Validate required fields
        if (binding.usernameField.text.isNullOrEmpty()) {
            binding.usernameField.error = getString(R.string.required_field)
            isValid = false
        }

        if (binding.emailField.text.isNullOrEmpty()) {
            binding.emailField.error = getString(R.string.required_field)
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailField.text.toString()).matches()) {
            binding.emailField.error = getString(R.string.invalid_email)
            isValid = false
        }

        if (binding.passwordField.text.isNullOrEmpty()) {
            binding.passwordField.error = getString(R.string.required_field)
            isValid = false
        } else if (binding.passwordField.text!!.length < 6) {
            binding.passwordField.error = getString(R.string.password_length)
            isValid = false
        }

        if (binding.confirmPasswordField.text.toString() != binding.passwordField.text.toString()) {
            binding.confirmPasswordField.error = getString(R.string.password_mismatch)
            isValid = false
        }

        if (!binding.termsCheckbox.isChecked) {
            Snackbar.make(binding.root, "You must agree to terms and conditions", Snackbar.LENGTH_SHORT).show()
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