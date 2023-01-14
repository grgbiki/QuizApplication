package com.bigurung.quizapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizQuestionDAO {
    @Insert
    fun addQuestion(question: QuizQuestion)

    @Insert
    fun addMultipleQuestion(vararg question: QuizQuestion)

    @Query("SELECT * FROM quizquestion ORDER BY RANDOM() LIMIT 15")
    fun getFifteenRandomQuestion(): LiveData<List<QuizQuestion>>
}