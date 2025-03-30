package com.example.sanskriti

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.sanskriti.databinding.ActivityQuizBinding
import com.example.sanskriti.databinding.ItemQuestionBinding
import com.example.sanskriti.models.Question

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var currentDifficulty: String
    private lateinit var questions: List<Question>
    private var score = 0
    private lateinit var questionPager: ViewPager2
    private lateinit var questionAdapter: QuestionPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentDifficulty = intent.getStringExtra("DIFFICULTY") ?: "easy"
        questions = QuestionBank.getQuestions(currentDifficulty)

        setupViewPager()
        setupClickListeners()
    }

    private fun setupViewPager() {
        questionPager = binding.questionPager
        questionAdapter = QuestionPagerAdapter(questions)
        questionPager.adapter = questionAdapter
        questionPager.isUserInputEnabled = true // Enable swiping
    }

    private fun setupClickListeners() {
        binding.nextButton.setOnClickListener {
            if (questionPager.currentItem < questions.size - 1) {
                questionPager.currentItem = questionPager.currentItem + 1
            } else {
                showResults()
            }
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

    inner class QuestionPagerAdapter(private val questions: List<Question>) : 
        RecyclerView.Adapter<QuestionPagerAdapter.QuestionViewHolder>() {

        inner class QuestionViewHolder(val binding: ItemQuestionBinding) : 
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
            val binding = ItemQuestionBinding.inflate(
                LayoutInflater.from(parent.context), 
                parent, 
                false
            )
            return QuestionViewHolder(binding)
        }

        override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
            val question = questions[position]
            holder.binding.questionNumber.text = getString(R.string.question, position + 1)
            holder.binding.questionText.text = question.text
            holder.binding.option1.text = question.options[0]
            holder.binding.option2.text = question.options[1]
            holder.binding.option3.text = question.options[2]
            holder.binding.option4.text = question.options[3]

            holder.binding.optionsGroup.setOnCheckedChangeListener { _, checkedId ->
                val selectedOption = when (checkedId) {
                    R.id.option1 -> 0
                    R.id.option2 -> 1
                    R.id.option3 -> 2
                    R.id.option4 -> 3
                    else -> -1
                }
                if (selectedOption == question.correctAnswer) {
                    score++
                    Toast.makeText(this@QuizActivity, "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@QuizActivity, 
                        "Wrong! Correct answer was ${question.options[question.correctAnswer]}", 
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun getItemCount(): Int = questions.size
    }
}
