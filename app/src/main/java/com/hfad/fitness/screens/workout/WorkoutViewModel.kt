package com.hfad.fitness.screens.workout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hfad.fitness.async.PendingResult
import com.hfad.fitness.async.Result
import com.hfad.fitness.base.*
import com.hfad.fitness.model.workouts.SQLiteWorkoutsRepository
import com.hfad.fitness.model.workouts.Workout
import kotlinx.coroutines.launch

class WorkoutViewModel (
    private val repository: SQLiteWorkoutsRepository
) : BaseViewModel(), WorkoutListActionListener {

    private val _navigateToExercisesEvent = MutableLiveEvent<Int>()
    val navigateToExercisesEvent: LiveEvent<Int> = _navigateToExercisesEvent

    private val _workoutList = MutableLiveData<Result<List<Workout>>> (PendingResult())
    val workoutList: LiveData<Result<List<Workout>>> = _workoutList

    override fun navigateToListExercises(workoutId: Int) {
        _navigateToExercisesEvent.publishEvent(workoutId)
    }

    fun loadWorkoutList() = viewModelScope.launch {
        try {
            into(_workoutList) { repository.getWorkouts() }
        } catch (e: Exception) {

        }
    }

    init {
        loadWorkoutList()
    }
}