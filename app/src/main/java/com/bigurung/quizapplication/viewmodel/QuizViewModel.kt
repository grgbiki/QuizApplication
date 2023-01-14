package com.bigurung.quizapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bigurung.quizapplication.db.QuizQuestion
import com.bigurung.quizapplication.repository.QuizRepository

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: QuizRepository =
        QuizRepository(application)
    private var allNotes: LiveData<List<QuizQuestion>>? = null

    fun getQuestions(): LiveData<List<QuizQuestion>>? {
        return allNotes
    }

    fun refreshQuestion() {
        allNotes = repository.getQuestions()
    }
}