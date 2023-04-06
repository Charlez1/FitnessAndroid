package com.hfad.fitness.screens.exerciseslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hfad.fitness.async.PendingResult
import com.hfad.fitness.async.Result
import com.hfad.fitness.base.BaseViewModel
import com.hfad.fitness.model.exercise.Exercise
import com.hfad.fitness.model.exercise.ExercisesCache
import com.hfad.fitness.model.exercise.SQLiteExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(
    private val exercisesCache: ExercisesCache,
    private val repository: SQLiteExerciseRepository,
    private val workoutId: Int
) : BaseViewModel() {

    private val _exerciseList = MutableLiveData<Result<List<Exercise>>> (PendingResult())
    val exerciseList: LiveData<Result<List<Exercise>>> = _exerciseList


    fun loadExerciseList() = viewModelScope.launch {
        try {
            into(_exerciseList) { repository.getExercises(workoutId) }

        } catch (e: Exception) {

        }
    }

    init {
        loadExerciseList()
    }

    override fun onCleared() {
        exercisesCache.clearExerciseCache()
        super.onCleared()
    }

}