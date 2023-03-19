package com.hfad.fitness.async


sealed class Result<T> {
}

class SuccessResult<T> (
    val data: T
) : Result<T>()

class ErrorResult<T>(
    val exception: Exception
) : Result<T>()

class PendingResult<T> : Result<T>()