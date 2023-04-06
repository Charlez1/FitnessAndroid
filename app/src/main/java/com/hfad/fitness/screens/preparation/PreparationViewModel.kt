package com.hfad.fitness.screens.preparation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.hfad.fitness.base.BaseViewModel
import com.hfad.fitness.model.exercise.Exercise
import com.hfad.fitness.model.exercise.ExercisesCache
import com.hfad.fitness.screens.currentexercise.CurrentExerciseViewModel

class PreparationViewModel(
    private val exercisesCache: ExercisesCache
) : BaseViewModel() {

    private val _currentExerciseState = MutableLiveData<ViewExerciseState>()
    val currentExerciseState: LiveData<ViewExerciseState> = _currentExerciseState

    fun getCurrentExercise() {
        val exerciseList = exercisesCache.getExerciseListCache()
        val currentExercisePosition = exercisesCache.getCurrentExercisePositionCache()
        if (exerciseList != null && currentExercisePosition != null) {
            _currentExerciseState.postValue(ViewExerciseState(
                currentExercise = exerciseList[currentExercisePosition],
                currentExercisePosition = currentExercisePosition,
                exerciseListSize = exerciseList.size
            ))
        }
    }

    data class ViewExerciseState(
        val currentExercise: Exercise,
        val currentExercisePosition: Int,
        val exerciseListSize: Int
    )


}