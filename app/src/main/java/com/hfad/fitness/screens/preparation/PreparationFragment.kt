package com.hfad.fitness.screens.preparation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hfad.fitness.R
import com.hfad.fitness.Repositories
import com.hfad.fitness.base.BaseFragment
import com.hfad.fitness.databinding.FragmentPreparationBinding
import com.hfad.fitness.screens.createViewModel

class PreparationFragment : BaseFragment(R.id.preparationFragment) {

    override val viewModel by createViewModel { PreparationViewModel(Repositories.ExercisesCache) }
    lateinit var binding: FragmentPreparationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPreparationBinding.inflate(layoutInflater, container, false)
        viewModel.getCurrentExercise()

        viewModel.currentExerciseState.observe(viewLifecycleOwner) {
            with(binding) {
                exerciseName.text = it.currentExercise.name
                exerciseCount.text = (it.currentExercisePosition + 1).toString() + "/" + it.exerciseListSize.toString()
                readyButton.setOnClickListener { onReadyPressed() }
            }
        }
        return binding.root
    }

    private fun onReadyPressed() {
        val direction = PreparationFragmentDirections.actionPreparationFragmentToCurrentExerciseFragment()
        findNavController().navigate(direction)
    }

}