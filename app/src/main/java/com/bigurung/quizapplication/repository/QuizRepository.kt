package com.bigurung.quizapplication.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.bigurung.quizapplication.db.QuestionDatabase
import com.bigurung.quizapplication.db.QuizQuestion
import com.bigurung.quizapplication.db.QuizQuestionDAO

class QuizRepository(application: Application) {

    private var quizQuestionDAO: QuizQuestionDAO

    private var questions: LiveData<List<QuizQuestion>>

    init {
        val database: QuestionDatabase = QuestionDatabase.invoke(
            application.applicationContext
        )
        quizQuestionDAO = database.getQuizQuestionDao()
        questions = quizQuestionDAO.getFifteenRandomQuestion()
    }

    fun getQuestions(): LiveData<List<QuizQuestion>> {
        return questions
    }
}