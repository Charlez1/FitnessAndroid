package com.hfad.fitness.screens.currentexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hfad.fitness.R
import com.hfad.fitness.Repositories
import com.hfad.fitness.base.BaseFragment
import com.hfad.fitness.databinding.FragmentCurrentExerciseBinding
import com.hfad.fitness.screens.createViewModel
import com.hfad.fitness.screens.exerciseslist.START_POSITION

class CurrentExerciseFragment : BaseFragment(R.id.currentExerciseFragment) {

    override val viewModel by createViewModel { CurrentExerciseViewModel(Repositories.ExercisesCache) }
    lateinit var binding: FragmentCurrentExerciseBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCurrentExerciseBinding.inflate(layoutInflater, container, false)

        setVisibilityButtons(View.GONE)

        viewModel.currentExerciseState.observe(viewLifecycleOwner) {
            with(binding) {
                exerciseName.text = it.currentExercise.name
                if (it.currentExercise.isCountSecond) {
                    exerciseCount.text = "00:" + it.currentExercise.count
                    doneOrPauseButton.text  = "pause"
                    doneOrPauseButton.setOnClickListener { onPausePressed() }
                } else {
                    exerciseCount.text = "x" + it.currentExercise.count
                    doneOrPauseButton.setOnClickListener { onDonePressed() }
                }

                skipButton.setOnClickListener { onSkipPressed() }

                if (it.currentExercisePosition != START_POSITION)
                    previousButton.setOnClickListener { onPreviousPressed() }
                else
                    previousButton.isClickable = false
                setVisibilityButtons(View.VISIBLE)
            }
        }

        return binding.root
    }

    private fun setVisibilityButtons(VISIBILITY: Int) {
        binding.doneOrPauseButton.visibility = VISIBILITY
        binding.skipButton.visibility = VISIBILITY
        binding.previousButton.visibility = VISIBILITY
    }

    private fun onDonePressed() {
        viewModel.currentExerciseState.observe(viewLifecycleOwner) {
            if (it.currentExercisePosition + 1 != it.exerciseListSize) {
                viewModel.onDone()
                findNavController().popBackStack()
            } else {
                //выход с экрана на экран окончания тренеровки
            }
        }
    }

    private fun onPausePressed() {
        viewModel.onPause()
    }

    private fun onSkipPressed() {
        viewModel.currentExerciseState.observe(viewLifecycleOwner) {
            if (it.currentExercisePosition + 1 != it.exerciseListSize) {
                viewModel.onDone()
                findNavController().popBackStack()
            } else {
                //выход с экрана на экран окончания тренеровки
                // TODO
                findNavController().popBackStack(R.id.workoutFragment, false)
            }
        }
    }

    private fun onPreviousPressed() {
        viewModel.onPrevious()
        findNavController().popBackStack()
    }
}