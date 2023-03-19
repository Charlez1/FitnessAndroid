package com.hfad.fitness.model.workouts

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.hfad.fitness.model.exercise.Exercise
import com.hfad.fitness.model.sqlite.AppSQLiteContract.WorkoutsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SQLiteWorkoutsRepository(
    private val db: SQLiteDatabase
) : WorkoutsRepository {

    override suspend fun getWorkouts(): List<Workout> = withContext(Dispatchers.IO) {
        delay(1000)
        return@withContext queryWorkouts()
    }

    private fun queryWorkouts(): List<Workout> {
        val cursor = getCursor()
        return cursor.use {
            val list = mutableListOf<Workout>()
            while (cursor.moveToNext()) {
                list.add(parseWorkout(cursor))
            }
            return@use list
        }
    }

    private fun parseWorkout(cursor: Cursor): Workout {
        return Workout(
            id = cursor.getInt(cursor.getColumnIndexOrThrow(WorkoutsTable.COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndexOrThrow(WorkoutsTable.COLUMN_WORKOUT_NAME)),
            imageBackground = cursor.getString(cursor.getColumnIndexOrThrow(WorkoutsTable.COLUMN_BACKGROUND))
        )
    }

    private fun getCursor(): Cursor {
        val sql = "SELECT * FROM ${WorkoutsTable.TABLE_NAME}"
        return db.rawQuery(sql, null)
    }
}