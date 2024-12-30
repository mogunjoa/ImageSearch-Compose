package com.mogun.imagesearch.network.repository

import com.mogun.imagesearch.data.KakaoImageSearchResponse
import com.mogun.imagesearch.network.api.NetworkService.apiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchImageRepository {
    fun searchImages(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Flow<KakaoImageSearchResponse> = flow {
        val response = apiService.searchImages(query, sort, page, size)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(it) // 성공적인 결과 방출
            } ?: throw Exception("Response body is null")
        } else {
            throw Exception("API Error: ${response.code()} - ${response.message()}")
        }
    }
}