package com.bigurung.quizapplication.ui.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bigurung.quizapplication.MainActivity
import com.bigurung.quizapplication.databinding.FragmentQuizBinding
import com.bigurung.quizapplication.db.QuizQuestion
import com.bigurung.quizapplication.ui.home.HomeFragmentDirections
import com.bigurung.quizapplication.viewmodel.QuizViewModel

class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null

    private val binding get() = _binding!!

    private var questions: List<QuizQuestion>? = null
    private var currentQuestionIndex = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val quizViewModel =
            ViewModelProvider(this)[QuizViewModel::class.java]
        _binding = FragmentQuizBinding.inflate(inflater, container, false)

        quizViewModel.refreshQuestion()
        quizViewModel.getQuestions()?.observe(viewLifecycleOwner) { questions ->
            if (questions != null) {
                if (questions.isNotEmpty()) {
                    this.questions = questions
                    loadQuestion()
                }
            }
        }

        binding.answerOptions.setOnCheckedChangeListener { _, _ ->
            binding.btnNext.text = "Next"
        }

        binding.btnNext.setOnClickListener {
            val quizQuestion = questions!![currentQuestionIndex]
            val checkedId = binding.answerOptions.checkedRadioButtonId
            if (checkedId >= 0) {
                quizQuestion.userAnswer =
                    binding.root.findViewById<RadioButton>(checkedId).text.toString()
            } else {
                quizQuestion.userAnswer = "Skipped"
            }

            binding.answerOptions.clearCheck()
            currentQuestionIndex++
            if (currentQuestionIndex == questions!!.size) {
                val action =
                    QuizFragmentDirections.actionShowResult(userResponse = questions!!.toTypedArray())
                findNavController().navigate(action)
            } else {
                loadQuestion()
                if (currentQuestionIndex == (questions!!.size - 1))
                    binding.btnNext.text = "Submit"
            }
        }

        binding.btnHome.setOnClickListener {
            val action =
                QuizFragmentDirections.actionHome()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun loadQuestion() {
        val quizQuestion = questions!![currentQuestionIndex]
        (activity as AppCompatActivity).supportActionBar?.title =
            "Question ${currentQuestionIndex + 1} of ${questions!!.size}"
        binding.question.text = quizQuestion.question
        var options = quizQuestion.options.split(",")

        options = options.shuffled()
        binding.optionOne.text = options[0]
        binding.optionTwo.text = options[1]
        binding.optionThree.text = options[2]
        binding.optionFour.text = options[3]

        binding.btnNext.text = "Skip"
    }

}