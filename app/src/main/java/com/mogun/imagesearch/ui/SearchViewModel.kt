package com.mogun.imagesearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mogun.imagesearch.data.KakaoImageSearchResponse
import com.mogun.imagesearch.network.repository.SearchImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed class UiState<out T> {
    object Empty : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

class SearchViewModel(): ViewModel() {
    private val searchRepository = SearchImageRepository()
    private val _imageSearchState = MutableStateFlow<UiState<KakaoImageSearchResponse>>(UiState.Empty)
    val imageSearchState: StateFlow<UiState<KakaoImageSearchResponse>> = _imageSearchState.asStateFlow()

    fun searchImages(query: String, sort: String = "accuracy", page: Int = 1, size: Int = 10) {
        viewModelScope.launch {
            searchRepository.searchImages(query, sort, page, size)
                .onStart {
                    // 로딩 상태
                    _imageSearchState.value = UiState.Loading
                }
                .catch { e ->
                    // 에러 상태
                    _imageSearchState.value = UiState.Error(e.message ?: "Unknown Error")
                }
                .collect { result ->
                    // 성공 상태
                    _imageSearchState.value = UiState.Success(result)
                }
        }
    }
}