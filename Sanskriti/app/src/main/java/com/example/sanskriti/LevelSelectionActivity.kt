package com.example.sanskriti

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sanskriti.databinding.ActivityLevelSelectionBinding

class LevelSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLevelSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.easyButton.setOnClickListener {
            startQuiz("easy")
        }
        binding.mediumButton.setOnClickListener {
            startQuiz("medium")
        }
        binding.hardButton.setOnClickListener {
            startQuiz("hard")
        }
    }

    private fun startQuiz(difficulty: String) {
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra("DIFFICULTY", difficulty)
        }
        startActivity(intent)
    }
}