package com.example.sanskriti.models

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswer: Int,
    val difficulty: String
)