package com.example.sanskriti

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sanskriti.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var currentDifficulty: String
    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentDifficulty = intent.getStringExtra("DIFFICULTY") ?: "easy"
        questions = QuestionBank.getQuestions(currentDifficulty)

        displayQuestion()
        setupClickListeners()
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            binding.questionText.text = getString(R.string.question, currentQuestionIndex + 1)
            binding.questionTextView.text = question.text
            binding.option1.text = question.options[0]
            binding.option2.text = question.options[1]
            binding.option3.text = question.options[2]
            binding.option4.text = question.options[3]
        } else {
            showResults()
        }
    }

    private fun setupClickListeners() {
        binding.option1.setOnClickListener { checkAnswer(0) }
        binding.option2.setOnClickListener { checkAnswer(1) }
        binding.option3.setOnClickListener { checkAnswer(2) }
        binding.option4.setOnClickListener { checkAnswer(3) }
        binding.nextButton.setOnClickListener { nextQuestion() }
    }

    private fun checkAnswer(selectedOption: Int) {
        val question = questions[currentQuestionIndex]
        if (selectedOption == question.correctAnswer) {
            score++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong! Correct answer was ${question.options[question.correctAnswer]}", 
                Toast.LENGTH_SHORT).show()
        }
        binding.nextButton.isEnabled = true
    }

    private fun nextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            displayQuestion()
            binding.nextButton.isEnabled = false
        } else {
            showResults()
        }
    }

    private fun showResults() {
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("SCORE", score)
            putExtra("TOTAL", questions.size)
            putExtra("DIFFICULTY", currentDifficulty)
        }
        startActivity(intent)
        finish()
    }
}