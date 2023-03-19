package com.hfad.fitness.model.exercise

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.hfad.fitness.model.sqlite.AppSQLiteContract
import com.hfad.fitness.model.workouts.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SQLiteExerciseRepository(
    private val db: SQLiteDatabase
): ExerciseRepository {

    override suspend fun getExercises(workoutId: Int): List<Exercise> = withContext(Dispatchers.IO) {
        delay(1000)
        return@withContext queryExercises(workoutId)
    }

    private fun queryExercises(workoutId: Int): List<Exercise> {
        val cursor = getCursor(workoutId)
        return cursor.use {
            val list = mutableListOf<Exercise>()
            while (cursor.moveToNext()) {
                list.add(parseExercise(cursor))
            }
            return@use list
        }
    }

    private fun parseExercise(cursor: Cursor): Exercise {
        return Exercise(
            id = cursor.getLong(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_NAME)),
            animation = cursor.getString(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_ANIMATION)),
            seconds = cursor.getLong(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_SECONDS)),
            amount = cursor.getLong(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_AMOUNTS))
        )
    }

    private fun getCursor(workoutId: Int): Cursor {
        val sql = "SELECT * FROM ${AppSQLiteContract.ExercisesTable} " +
                "INNER JOIN ${AppSQLiteContract.WorkoutsExercisesTable} " +
                "ON ${AppSQLiteContract.WorkoutsExercisesTable.COLUMN_WORKOUT_ID} = ${workoutId}" +
                "AND ${AppSQLiteContract.WorkoutsExercisesTable.COLUMN_EXERCISE_ID} = ${AppSQLiteContract.WorkoutsExercisesTable.COLUMN_EXERCISE_ID};"
        return db.rawQuery(sql, null)
    }
}