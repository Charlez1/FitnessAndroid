package com.hfad.fitness.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

import com.hfad.fitness.async.Result
import com.hfad.fitness.async.ErrorResult
import com.hfad.fitness.async.SuccessResult
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {


    private val coroutineContext = SupervisorJob() + Dispatchers.Main.immediate + CoroutineExceptionHandler { _, throwable ->
        // you can add some exception handling here
    }

    protected val viewModelScope = CoroutineScope(coroutineContext)


    fun <T> into(liveResult: MutableLiveData<Result<T>>, block: suspend () -> T) {
        viewModelScope.launch {
            try {
                liveResult.postValue(SuccessResult(block()))
            } catch (e: Exception) {
                if (e !is CancellationException) liveResult.postValue(ErrorResult(e))
            }
        }
    }

}