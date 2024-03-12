package com.challenge.hwg.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.domain.usecase.ExchangeRatesUseCase
import com.challenge.hwg.presentation.PropertyDetailsState.Error
import com.challenge.hwg.presentation.PropertyDetailsState.Loaded
import com.challenge.model.Price
import com.challenge.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsModel @Inject constructor(
    private val exchangeRatesUseCase: ExchangeRatesUseCase
) : ViewModel() {

    val uiState = BehaviorSubject.create<PropertyDetailsState>()

    fun convert(price: Price, to: Price.Currency) {
        when {
            // do nothing,  cannot convert to EUR from EUR
            price.currency != Price.Currency.EUR -> Unit

            // go back to default EUR pricing
            to == price.currency -> uiState.onNext(Loaded(price))
            
            else -> {
                uiState.onNext(PropertyDetailsState.Converting(price)) // push loading state
                viewModelScope.launch {
                    exchangeRatesUseCase().map {
                        // create appropriate ui state
                        when (it) {
                            is Result.Failure -> Error(it.message, price)
                            is Result.Success -> Loaded(it.data.convert(price, to))
                        }
                    }.subscribe(uiState::onNext).dispose() // stop stream after consumption
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        // when this ViewModel is no longer used,
        // complete the stream, stream disposed automatically after onComplete
        uiState.onComplete()
    }

}