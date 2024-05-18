package kz.petprojects.mechta.ui.smartphone

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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

    private val _isLoadingMore = mutableStateOf(false)
    val isLoadingMore: State<Boolean> get() = _isLoadingMore

    private var currentPage = 1

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> get() = _searchQuery

    private val _filteredSmartphones = MutableStateFlow<List<Smartphone>>(emptyList())
    val filteredSmartphones: MutableStateFlow<List<Smartphone>> = _filteredSmartphones

    init {
        fetchData()
    }

    private fun fetchData(page: Int = 1) {
        viewModelScope.launch {
            try {
                if (page == 1) _isLoading.value = true else _isLoadingMore.value = true
                val newSmartphones = getSmartphonesUseCase.invoke(page)
                _smartphonesList.value += newSmartphones
                filterSmartphones(_searchQuery.value)
                _isLoading.value = false
                _isLoadingMore.value = false
            } catch (e: Exception) {
                Log.e("smartphone view model", e.message.toString())
                _isLoading.value = false
                _isLoadingMore.value = false
            }
        }
    }

    fun loadMoreData() {
        if (!_isLoadingMore.value) {
            currentPage++
            fetchData(currentPage)
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterSmartphones(query)
    }

    private fun filterSmartphones(query: String) {
        val filteredList = if (query.isEmpty()) {
            _smartphonesList.value
        } else {
            _smartphonesList.value.filter { it.name.contains(query, ignoreCase = true) }
        }
        _filteredSmartphones.value = filteredList
    }
}
