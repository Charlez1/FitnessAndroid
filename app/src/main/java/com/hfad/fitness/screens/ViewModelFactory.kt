package com.hfad.fitness.screens

import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.hfad.fitness.Repositories
import com.hfad.fitness.base.BaseFragment
import java.lang.reflect.Constructor

// функция, которая вызывается из фрагмента для инициализации viewModel
inline fun <reified VM : ViewModel> BaseFragment.createViewModel() = viewModels<VM> {
    val dependencies = listOf(Repositories.WorkoutsRepository, Repositories.ExerciseRepository)
    return@viewModels ViewModelFactory(dependencies, this)
}

class ViewModelFactory (
    private val dependencies: List<Any>,
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory (owner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        // поулчаем список всех конструкторов конкретной viewModel, которую создаем
        val constructors = modelClass.constructors
        // выбираем конструктор с максимальным количеством параметров(он обычно один)
        val constructor = constructors.maxByOrNull { it.typeParameters.size }!!

        // из списка зависимостей удаляем те что не используются в конструкторе , а также сортируем его в соответсвии с порядком в конструкторе
        val arguments = findArg(constructor, dependencies)

        // приводим список к массиву и распаковываем его с помощью "*" чтобы передать в конструктор 
        return constructor.newInstance( *(arguments.toTypedArray()) ) as T
    }

    fun findArg(constructor: Constructor<*>, dependencies: List<Any>): List<Any> {
        val args = mutableListOf<Any>()
        // проходимся по конструктору и для каждого класса параметра от первого к последнему создаем соответствующую этому классу зависимость
        // при этом мы можем в конструкторе viewModel не указывать не нужные нам зависимости
        constructor.parameterTypes.forEach { parameterClass ->
            val dependency = dependencies.first {
                parameterClass.isAssignableFrom(it.javaClass) }
            args.add(dependency)
        }
        return args
    }

}