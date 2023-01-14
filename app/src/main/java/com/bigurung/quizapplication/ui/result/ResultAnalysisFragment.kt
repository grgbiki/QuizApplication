package com.bigurung.quizapplication.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigurung.quizapplication.databinding.FragmentResultAnalysisBinding
import com.bigurung.quizapplication.db.QuizQuestion

class ResultAnalysisFragment : Fragment() {
    private var _binding: FragmentResultAnalysisBinding? = null

    private val binding get() = _binding!!
    private var questions: Array<QuizQuestion>? = null

    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultAnalysisBinding.inflate(inflater, container, false)
        questions = args.userResponse

        val adapter = ResultAnalysisRecyclerAdapter(questions!!, requireContext())

        binding.resultAnalysisRecyclerView.layoutManager = LinearLayoutManager(context);
        binding.resultAnalysisRecyclerView.adapter = adapter
        binding.resultAnalysisRecyclerView.isNestedScrollingEnabled = false

        return binding.root
    }

}