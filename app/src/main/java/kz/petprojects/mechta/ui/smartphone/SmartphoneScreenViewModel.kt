package kz.petprojects.mechta.ui.smartphone

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kz.petprojects.mechta.domain.model.getSmartphones.Smartphone
import kz.petprojects.mechta.domain.use_cases.GetSmartphonesUseCase

class SmartphoneScreenViewModel(
    private val getSmartphonesUseCase: GetSmartphonesUseCase
) : ViewModel() {

    private val _smartphonesList = MutableStateFlow<List<Smartphone>>(emptyList())
    val smartphone: MutableStateFlow<List<Smartphone>> = _smartphonesList


    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                //_smartphonesList.addAll(getSmartphonesUseCase.invoke())
                _smartphonesList.value = getSmartphonesUseCase.invoke()
                Log.e("viewmodel", _smartphonesList.toString())
                _isLoading.value = false
            } catch (e: Exception) {
                Log.e("smartphone view model", e.message.toString())
                _isLoading.value = true
            }
        }
    }
}