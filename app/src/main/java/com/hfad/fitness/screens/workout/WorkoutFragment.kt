package com.hfad.fitness.screens.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.fitness.R
import com.hfad.fitness.databinding.FragmentWorkoutBinding
import com.hfad.fitness.base.BaseFragment
import com.hfad.fitness.base.observeEvent
import com.hfad.fitness.screens.createViewModel

class WorkoutFragment : BaseFragment(R.layout.fragment_workout) {

    override val viewModel by createViewModel<WorkoutViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWorkoutBinding.inflate(inflater, container,false)
        val adapter = WorkoutAdapter(viewModel)

        viewModel.workoutList.observe(requireActivity(), Observer { result ->
            renderSimpleResult(
                root = binding.root,
                result = result,
                onSuccess = { adapter.workoutList = it }
            )
        })

        viewModel.navigateToExercisesEvent.observeEvent(viewLifecycleOwner) { workoutId ->
            val direction = WorkoutFragmentDirections.actionWorkoutFragmentToExerciseFragment(workoutId)
            findNavController().navigate(direction)
        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }

}