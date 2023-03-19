package com.hfad.fitness

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hfad.fitness.model.exercise.Exercise
import com.hfad.fitness.model.exercise.ExerciseRepository
import com.hfad.fitness.model.exercise.SQLiteExerciseRepository
import com.hfad.fitness.model.workouts.SQLiteWorkoutsRepository
import com.hfad.fitness.model.sqlite.AppSQLiteHelper

object Repositories {

    private lateinit var applicationContext: Context

    private val database: SQLiteDatabase by lazy<SQLiteDatabase> {
        AppSQLiteHelper(applicationContext).writableDatabase
    }

    val WorkoutsRepository: SQLiteWorkoutsRepository by lazy {
        SQLiteWorkoutsRepository(database)
    }
    val ExerciseRepository: SQLiteExerciseRepository by lazy {
        SQLiteExerciseRepository(database)
    }

    fun init(context: Context) {
        applicationContext = context
    }

}