package com.example.sanskriti

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sanskriti.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        val difficulty = intent.getStringExtra("DIFFICULTY") ?: "easy"

        displayResults(score, total, difficulty)
        setupClickListeners()
    }

    private fun displayResults(score: Int, total: Int, difficulty: String) {
        binding.scoreText.text = getString(R.string.score, score, total)
        binding.difficultyText.text = "Difficulty: ${difficulty.capitalize()}"
        
        val percentage = (score.toFloat() / total.toFloat()) * 100
        when {
            percentage >= 80 -> binding.feedbackText.text = "Excellent! You're a Sanskriti expert!"
            percentage >= 50 -> binding.feedbackText.text = "Good job! Keep learning!"
            else -> binding.feedbackText.text = "Keep practicing to improve!"
        }
    }

    private fun setupClickListeners() {
        binding.tryAgainButton.setOnClickListener {
            finish() // Goes back to QuizActivity
        }
        
        binding.homeButton.setOnClickListener {
            val intent = Intent(this, LevelSelectionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}