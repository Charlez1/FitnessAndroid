package com.hfad.fitness.base

import android.text.Layout
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.hfad.fitness.R

import com.hfad.fitness.async.Result
import com.hfad.fitness.async.SuccessResult
import com.hfad.fitness.async.ErrorResult
import com.hfad.fitness.async.PendingResult
import com.hfad.fitness.databinding.PartResultBinding


abstract class BaseFragment(layout: Int): Fragment(layout) {

    abstract val viewModel: BaseViewModel

    fun <T> renderResult(root: ViewGroup,
                         result: Result<T>,
                         onPending: () -> Unit,
                         onError: (Exception) -> Unit,
                         onSuccess: (T) -> Unit) {

        root.children.forEach { it.visibility = View.GONE }
        when (result) {
            is SuccessResult -> onSuccess(result.data)
            is ErrorResult -> onError(result.exception)
            is PendingResult -> onPending()
        }

    }

    fun <T> renderSimpleResult(root: ViewGroup, result: Result<T>, onSuccess: (T) -> Unit) {
        val binding = PartResultBinding.bind(root)

        renderResult(
            root = root,
            result = result,
            onPending = {
                binding.progressBar.visibility = View.VISIBLE
            },
            onError = {
                binding.errorContainer.visibility = View.VISIBLE
            },
            onSuccess = { successData ->
                root.children
                    .filter { it.id != R.id.progressBar && it.id != R.id.errorContainer }
                    .forEach { it.visibility = View.VISIBLE }
                onSuccess(successData)
            }
        )
    }


}