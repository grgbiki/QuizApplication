package com.bigurung.quizapplication.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class QuizQuestion(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val question: String,
    val options: String,
    val answer: String
) : Parcelable {
    @Ignore
    var userAnswer: String? = null
}