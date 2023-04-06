package com.hfad.fitness.model.exercise

import com.github.javafaker.Bool
import com.hfad.fitness.screens.exerciseslist.START_POSITION

class ExercisesCache {

    private var exerciseListCache : List<Exercise>? = null
    private var currentExercisePositionCache: Int? = null

    fun setExerciseListCache(list: List<Exercise>) {
        this.exerciseListCache = list
    }
    fun incrementCurrentExercisePositionCache() {
        if (currentExercisePositionCache != null)
            currentExercisePositionCache = currentExercisePositionCache?.inc()
        else
            currentExercisePositionCache = START_POSITION

    }
    fun decrementCurrentExercisePositionCache() {
        if (currentExercisePositionCache != null)
            currentExercisePositionCache = currentExercisePositionCache?.dec()
        else
            currentExercisePositionCache = exerciseListCache?.size

    }

    fun getExerciseListCache(): List<Exercise>?{
        return exerciseListCache
    }
    fun getCurrentExercisePositionCache() : Int? {
        return currentExercisePositionCache
    }

    fun clearExerciseCache() {
        exerciseListCache = null
        currentExercisePositionCache = null
    }
}