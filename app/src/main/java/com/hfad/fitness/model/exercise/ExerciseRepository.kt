package com.hfad.fitness.model.exercise

import com.hfad.fitness.model.workouts.Workout

interface ExerciseRepository {

    suspend fun getExercises(workoutId: Int): List<Exercise>
}