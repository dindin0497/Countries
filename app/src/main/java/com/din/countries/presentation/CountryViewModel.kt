package com.din.countries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.din.countries.domain.CountryClient
import com.din.countries.domain.DetailCountry
import com.din.countries.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val client: CountryClient) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailCountry ? = null
    )

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            _state.update {
                it.copy(
                    countries = client.getCountries().sortedBy { it.name },
                    isLoading = false)
            }
        }
    }

    fun selectCountry(code: String){
        viewModelScope.launch {
            _state.update {
                it.copy(selectedCountry = client.getCountry(code))
            }
        }
    }

    fun dismissCountryDialog(){
        _state.update {
            it.copy(selectedCountry = null)
        }
    }

}