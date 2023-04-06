package com.hfad.fitness.screens.exerciseslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.fitness.R
import com.hfad.fitness.Repositories
import com.hfad.fitness.base.BaseFragment
import com.hfad.fitness.databinding.FragmentExerciseBinding
import com.hfad.fitness.screens.createViewModel

const val EXERCISE_PREFERENCES = "EXERCISE_PREFERENCES"
const val POSITION_IN_EXERCISES_LIST = "POSITION_IN_EXERCISES_LIST"
const val START_POSITION = 0

class ExerciseFragment : BaseFragment(R.layout.fragment_exercise) {

    override val viewModel by createViewModel { ExerciseViewModel(Repositories.ExercisesCache, Repositories.ExerciseRepository, getWorkoutId()) }
    lateinit var binding: FragmentExerciseBinding

    private val args by navArgs<ExerciseFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        val adapter = ExerciseAdapter()

        binding.startButton.visibility = View.GONE

        viewModel.exerciseList.observe(requireActivity()) { result->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onSuccess = {
                    adapter.exerciseList = it
                    binding.startButton.visibility = View.VISIBLE
                }
            )
        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        binding.startButton.setOnClickListener { onStartButtonPressed() }
        return binding.root
    }

    private fun onStartButtonPressed() {
        val direction = ExerciseFragmentDirections.actionExerciseFragmentToPreparationFragment()
        findNavController().navigate(direction)
    }

    private fun getWorkoutId(): Int = args.workoutId

}