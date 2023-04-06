package com.hfad.fitness.screens.exerciseslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.fitness.databinding.ItemExerciseBinding
import com.hfad.fitness.model.exercise.Exercise

class ExerciseAdapter() : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    var exerciseList: List<Exercise> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemExerciseBinding.inflate(inflater, parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun getItemCount(): Int = exerciseList.size

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        var exercise = exerciseList[position]
        with(holder.binding) {
            exerciseMainText.setText(exercise.name)
            exerciseInfo.setText(
                if (exercise.isCountSecond)
                    "00:" + exercise.count.toString()
                else
                    "x " + exercise.count.toString()
            )
        }
    }

    class ExerciseViewHolder(
        val binding: ItemExerciseBinding
    ) : RecyclerView.ViewHolder(binding.root)
}