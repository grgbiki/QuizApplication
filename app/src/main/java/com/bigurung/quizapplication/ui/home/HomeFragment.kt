package com.bigurung.quizapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bigurung.quizapplication.R
import com.bigurung.quizapplication.databinding.FragmentHomeBinding
import com.bigurung.quizapplication.databinding.FragmentQuizBinding
import com.bigurung.quizapplication.viewmodel.QuizViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnStartQuiz.setOnClickListener {
            val action = HomeFragmentDirections.actionStartQuiz()
            findNavController().navigate(action)
        }
        return binding.root
    }
}