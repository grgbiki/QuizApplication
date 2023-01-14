package com.bigurung.quizapplication.db

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bigurung.quizapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class PrepopulateDbCallback(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            prePopulateQuestions(context)
        }
    }

    private fun prePopulateQuestions(context: Context) {
        try {
            val quizQuestionDao = QuestionDatabase.invoke(context).getQuizQuestionDao()

            val userList: JSONArray =
                context.resources.openRawResource(R.raw.questions).bufferedReader().use {
                    JSONArray(it.readText())
                }

            userList.takeIf { it.length() > 0 }?.let { list ->
                for (index in 0 until list.length()) {
                    val question = list.getJSONObject(index)
                    quizQuestionDao.addQuestion(
                        QuizQuestion(
                            0,
                            question.getString("question"),
                            question.getString("options"),
                            question.getString("answer")
                        )
                    )

                }
            }
        } catch (exception: Exception) {
            Log.e(
                "Quiz App",
                exception.localizedMessage ?: "failed to pre-populate questions into database"
            )
        }
    }
}