package com.hfad.fitness.screens.workout

import com.hfad.fitness.databinding.ItemWorkoutBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.hfad.fitness.R
import com.hfad.fitness.model.workouts.Workout


interface WorkoutListActionListener {

    fun navigateToListExercises(workoutId: Int)
}

class WorkoutAdapter(
    private val actionListener: WorkoutListActionListener
) : RecyclerView.Adapter<WorkoutAdapter.SettingViewHolder>(), View.OnClickListener {

    var workoutList: List<Workout> = emptyList()

    override fun onClick(view: View) {
        val workout = view.tag as Workout
        actionListener.navigateToListExercises(workout.id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWorkoutBinding.inflate(inflater, parent, false)
        binding.clickableLayout.setOnClickListener(this)
        return SettingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        val workout = workoutList[position]
        holder.itemView.tag = workout
        with(holder.binding) {
            workoutMainText.text = workout.name
            workoutImage.background = R.drawable.download.toDrawable()
        }
    }

    override fun getItemCount(): Int {
        return workoutList.size
    }


    class SettingViewHolder(
        val binding: ItemWorkoutBinding
    ) : RecyclerView.ViewHolder(binding.root)
}