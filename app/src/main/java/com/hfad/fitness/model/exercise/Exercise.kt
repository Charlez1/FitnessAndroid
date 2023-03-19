package com.hfad.fitness.model.exercise

data class Exercise(
    val id: Long,
    var name: String,
    var animation: String,
    var seconds: Long,
    val amount: Long
)
