package com.hfad.fitness.model.exercise

data class Exercise(
    val id: Int,
    var name: String,
    var animation: String,
    var isCountSecond: Boolean,
    var count: Long
)
