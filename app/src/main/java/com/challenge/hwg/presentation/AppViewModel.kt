package com.challenge.hwg.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.usecase.GetPropertiesUseCase
import com.challenge.hwg.presentation.PropertyListState.Error
import com.challenge.hwg.presentation.PropertyListState.Success
import com.challenge.model.PropertyList
import com.challenge.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val getPropertiesUseCase: GetPropertiesUseCase
) : ViewModel() {

    val uiState = BehaviorSubject.create<PropertyListState>()

    init {
        refresh()
    }

    fun refresh() {
        uiState.onNext(PropertyListState.Loading) // push loading state
        viewModelScope.launch {
            getPropertiesUseCase().map {
                // create appropriate ui state
                when (it) {
                    is Result.Failure<*> -> Error(it.message)
                    is Result.Success<PropertyList> -> Success(it.data.properties, it.data.city)
                }
            }.subscribe(uiState::onNext).dispose() // stop stream after consumption
        }
    }

    override fun onCleared() {
        super.onCleared()
        // when this ViewModel is no longer used,
        // complete the stream, stream disposed automatically after onComplete
        uiState.onComplete()
    }

}