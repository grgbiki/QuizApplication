package com.bigurung.quizapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [QuizQuestion::class],
    version = 1
)
abstract class QuestionDatabase() : RoomDatabase() {
    abstract fun getQuizQuestionDao(): QuizQuestionDAO

    companion object {
        @Volatile
        private var instance: QuestionDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            QuestionDatabase::class.java,
            "quizDatabase"
        ).addCallback(PrepopulateDbCallback(context))
            .build()
    }
}