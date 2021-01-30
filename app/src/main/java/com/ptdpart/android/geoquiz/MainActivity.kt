package com.ptdpart.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate bundled")

        questionTextView = findViewById(R.id.question_text_view)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }
        updateQuestion()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val toastMessageResId = if (userAnswer == correctAnswer) {
            quizViewModel.addScore()
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, toastMessageResId, Toast.LENGTH_SHORT).show()
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    private fun updateQuestion() {
        if (quizViewModel.currentIndex == quizViewModel.questionBank.size) {
            Toast.makeText(this,
                getString(R.string.correct_answers_toast, quizViewModel.correctAnswers),
                Toast.LENGTH_SHORT).show()
            quizViewModel.resetScore()
        }
        questionTextView.setText(quizViewModel.currentQuestionText)
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }
}