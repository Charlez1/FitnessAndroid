package com.hfad.fitness

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hfad.fitness.model.exercise.ExercisesCache
import com.hfad.fitness.model.exercise.SQLiteExerciseRepository
import com.hfad.fitness.model.sqlite.AppSQLiteHelper
import com.hfad.fitness.model.workouts.SQLiteWorkoutsRepository


object Repositories {

    private lateinit var applicationContext: Context

    private val database: SQLiteDatabase by lazy<SQLiteDatabase> {
        AppSQLiteHelper(applicationContext).readableDatabase
    }

    val WorkoutsRepository: SQLiteWorkoutsRepository by lazy {
        SQLiteWorkoutsRepository(database)
    }
    val ExerciseRepository: SQLiteExerciseRepository by lazy {
        SQLiteExerciseRepository(database, ExercisesCache)
    }
    val ExercisesCache: ExercisesCache by lazy {
        ExercisesCache()
    }

    fun init(context: Context) {
        applicationContext = context
    }

}