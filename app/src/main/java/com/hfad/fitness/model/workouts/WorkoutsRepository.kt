package com.hfad.fitness.model.workouts


interface WorkoutsRepository {

    suspend fun getWorkouts(): List<Workout>
}