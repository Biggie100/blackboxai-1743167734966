package com.example.sanskriti

import com.example.sanskriti.models.Question

object QuestionBank {
    fun getQuestions(difficulty: String): List<Question> {
        return when (difficulty) {
            "easy" -> easyQuestions
            "medium" -> mediumQuestions
            "hard" -> hardQuestions
            else -> easyQuestions
        }
    }

    private val easyQuestions = listOf(
        Question(
            1,
            "What is the capital of India?",
            listOf("Mumbai", "Delhi", "Kolkata", "Chennai"),
            1,
            "easy"
        ),
        Question(
            2,
            "Which river is considered sacred in Hinduism?",
            listOf("Yamuna", "Ganga", "Kaveri", "Godavari"),
            1,
            "easy"
        ),
        Question(
            3,
            "Who is known as the Father of the Indian Constitution?",
            listOf("Mahatma Gandhi", "Jawaharlal Nehru", 
                  "B.R. Ambedkar", "Sardar Patel"),
            2,
            "easy"
        )
    )

    private val mediumQuestions = listOf(
        Question(
            4,
            "Who wrote the Indian National Anthem?",
            listOf("Rabindranath Tagore", "Bankim Chandra Chatterjee", 
                  "Sarojini Naidu", "Mahatma Gandhi"),
            0,
            "medium"
        ),
        Question(
            5,
            "Which ancient Indian university was established in the 5th century?",
            listOf("Takshashila", "Nalanda", "Vikramshila", "Odantapuri"),
            1,
            "medium"
        ),
        Question(
            6,
            "Which festival is known as the Festival of Lights?",
            listOf("Holi", "Diwali", "Dussehra", "Navratri"),
            1,
            "medium"
        )
    )

    private val hardQuestions = listOf(
        Question(
            7,
            "Which ancient Indian text is considered the oldest scripture?",
            listOf("Puranas", "Upanishads", "Vedas", "Bhagavad Gita"),
            2,
            "hard"
        ),
        Question(
            8,
            "Who was the author of Arthashastra?",
            listOf("Chanakya", "Panini", "Aryabhata", "Varahamihira"),
            0,
            "hard"
        ),
        Question(
            9,
            "Which Indian ruler built the Konark Sun Temple?",
            listOf("Ashoka", "Harsha", "Narasimhadeva I", "Rajaraja Chola"),
            2,
            "hard"
        )
    )
}