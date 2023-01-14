package com.bigurung.quizapplication.ui.result

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bigurung.quizapplication.R
import com.bigurung.quizapplication.databinding.ResultAnalysisRecyclerItemBinding
import com.bigurung.quizapplication.db.QuizQuestion

class ResultAnalysisRecyclerAdapter(var userResponse: Array<QuizQuestion>, val context: Context) :
    RecyclerView.Adapter<ResultAnalysisRecyclerAdapter.ResultAnalysisViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAnalysisViewHolder {
        val binding = ResultAnalysisRecyclerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultAnalysisViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ResultAnalysisViewHolder, position: Int) {
        holder.bind(userResponse.get(position))
    }

    override fun getItemCount() = userResponse.size

    class ResultAnalysisViewHolder(
        private val binding: ResultAnalysisRecyclerItemBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: QuizQuestion) {
            var backgroundColor: Int =
                when (question.userAnswer) {
                    "Skipped" -> {
                        ContextCompat.getColor(context, R.color.gray)
                    }
                    question.answer -> {
                        ContextCompat.getColor(context, R.color.green)
                    }
                    else -> {
                        ContextCompat.getColor(context, R.color.red)
                    }
                }
            binding.root.setBackgroundColor(backgroundColor)
            binding.questionText.text = question.question
            binding.yourAnswerText.text = question.answer
            binding.correctAnswerText.text = question.userAnswer
        }
    }
}