package com.ptdpart.android.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    init {
        Log.d(TAG, "QuizViewModel created")
    }

    var currentIndex = 0
        private set
    var correctAnswers = 0
        private set

    internal val questionBank = listOf(
            Question(R.string.question_1, true),
            Question(R.string.question_2, false),
            Question(R.string.question_3, false),
            Question(R.string.question_4, true),
            Question(R.string.question_5, false)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex++
    }

    fun resetScore() {
        currentIndex = 0
        correctAnswers = 0
    }

    fun addScore() = correctAnswers++
}