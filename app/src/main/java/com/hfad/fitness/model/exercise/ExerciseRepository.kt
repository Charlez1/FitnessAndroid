package com.hfad.fitness.model.exercise

interface ExerciseRepository {

    suspend fun getExercises(workoutId: Int): List<Exercise>

}