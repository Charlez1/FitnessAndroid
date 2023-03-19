package com.hfad.fitness.model.sqlite

object AppSQLiteContract {

    object WorkoutsTable {
        const val TABLE_NAME = "workouts"
        const val COLUMN_ID = "id"
        const val COLUMN_WORKOUT_NAME = "workout_name"
        const val COLUMN_BACKGROUND = "image_background"
    }

    object ExercisesTable {
        const val TABLE_NAME = "exercises"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_ANIMATION = "animation"
        const val COLUMN_SECONDS = "seconds"
        const val COLUMN_AMOUNTS = "amounts"
    }

    object WorkoutsExercisesTable {
        const val TABLE_NAME = "workouts_exercises"
        const val COLUMN_WORKOUT_ID = "workout_id"
        const val COLUMN_EXERCISE_ID = "exercise_id"
    }
}