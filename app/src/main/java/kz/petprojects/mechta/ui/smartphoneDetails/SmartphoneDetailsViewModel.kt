package kz.petprojects.mechta.ui.smartphoneDetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kz.petprojects.mechta.domain.model.SmartphoneDetails
import kz.petprojects.mechta.domain.use_cases.GetSmartphoneDetailsUseCase

class SmartphoneDetailsViewModel(
    private val getSmartphoneDetailsUseCase: GetSmartphoneDetailsUseCase
): ViewModel() {

    private val _smartphoneDetails = MutableStateFlow<SmartphoneDetails?>(null)
    val smartphoneDetails: StateFlow<SmartphoneDetails?> = _smartphoneDetails

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading


    fun fetchSmartphoneDetails(code: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val details = getSmartphoneDetailsUseCase.invoke(code)
                _smartphoneDetails.value = details
            } catch (e: Exception) {
                Log.e("smartphone details view model", "${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

}