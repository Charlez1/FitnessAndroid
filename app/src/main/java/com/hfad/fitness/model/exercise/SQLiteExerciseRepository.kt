package com.hfad.fitness.model.exercise

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.hfad.fitness.model.sqlite.AppSQLiteContract
import com.hfad.fitness.screens.exerciseslist.START_POSITION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SQLiteExerciseRepository(
    private val db: SQLiteDatabase,
    private val exercisesCache: ExercisesCache
): ExerciseRepository {


    override suspend fun getExercises(workoutId: Int): List<Exercise> = withContext(Dispatchers.IO) {
        delay(1000)
        val exerciseList = queryExercises(workoutId)
        exercisesCache.setExerciseListCache(exerciseList)
        exercisesCache.incrementCurrentExercisePositionCache()
        return@withContext exerciseList
    }

    private fun queryExercises(workoutId: Int): List<Exercise> {
        val cursor = getCursorByWorkoutId(workoutId)
        return cursor.use {
            val list = mutableListOf<Exercise>()
            while (it.moveToNext()) {
                list.add(parseExercise(it))
            }
            return@use list
        }
    }

    private fun parseExercise(cursor: Cursor): Exercise {
        return Exercise(
            id = cursor.getInt(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_ID)),
            name = cursor.getString(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_NAME)),
            animation = cursor.getString(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_ANIMATION)),
            count = cursor.getLong(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_COUNT)),
            isCountSecond = cursor.getInt(cursor.getColumnIndexOrThrow(AppSQLiteContract.ExercisesTable.COLUMN_IS_COUNT_SECONDS)) == 1
        )
    }

    private fun getCursorByWorkoutId(workoutId: Int): Cursor {
        val sql = "SELECT * FROM ${AppSQLiteContract.ExercisesTable.TABLE_NAME} " +
                "INNER JOIN ${AppSQLiteContract.WorkoutsExercisesTable.TABLE_NAME} " +
                "ON ${AppSQLiteContract.WorkoutsExercisesTable.COLUMN_WORKOUT_ID} = ${workoutId} " +
                "AND ${AppSQLiteContract.WorkoutsExercisesTable.COLUMN_EXERCISE_ID} = ${AppSQLiteContract.ExercisesTable.COLUMN_ID};"
//        val sql = "SELECT * FROM exercises JOIN workouts_exercises ON workout_id = 2 AND exercise_id = exercises.id"
        return db.rawQuery(sql, null)
    }
}