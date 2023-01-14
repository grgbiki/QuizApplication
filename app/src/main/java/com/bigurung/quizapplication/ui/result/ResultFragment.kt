package com.bigurung.quizapplication.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bigurung.quizapplication.R
import com.bigurung.quizapplication.databinding.FragmentResultBinding
import com.bigurung.quizapplication.db.QuizQuestion

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null

    private val binding get() = _binding!!
    private var questions: Array<QuizQuestion>? = null

    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        questions = args.userResponse

        var correctAnswer = 0
        var wrongAnswer = 0
        var skippedQuestion = 0
        if (questions!!.isNotEmpty()) {
            questions!!.forEach {
                if (it.userAnswer.equals("Skipped"))
                    skippedQuestion++
                else if (it.answer == it.userAnswer)
                    correctAnswer++
                else
                    wrongAnswer++
            }
        }
        binding.totalQuestionText.text = questions!!.size.toString()
        binding.correctAnswerText.text = correctAnswer.toString()
        binding.wrongAnswerText.text = wrongAnswer.toString()
        binding.skippedQuestionText.text = skippedQuestion.toString()
        binding.scoreText.text = "$correctAnswer/${questions!!.size.toString()}"

        binding.btnTryAgain.setOnClickListener {
            val action =
                ResultFragmentDirections.actionRetryQuiz()
            findNavController().navigate(action)
        }

        binding.btnResultAnalysis.setOnClickListener {
            val action =
                ResultFragmentDirections.actionShowAnalysis(userResponse = questions!!)
            findNavController().navigate(action)
        }

        binding.btnHome.setOnClickListener {
            val action =
                ResultFragmentDirections.actionReturnHome()
            findNavController().navigate(action)
        }
        return binding.root
    }
}