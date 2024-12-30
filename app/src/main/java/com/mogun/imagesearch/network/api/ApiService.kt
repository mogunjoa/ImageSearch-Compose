package com.mogun.imagesearch.network.api


import com.mogun.imagesearch.data.KakaoImageSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/image")
    suspend fun searchImages(
        @Query("query") query: String, // 검색어 (필수)
        @Query("sort") sort: String = "accuracy", // 정렬 방식 (기본: accuracy)
        @Query("page") page: Int = 1, // 페이지 번호 (1~50, 기본: 1)
        @Query("size") size: Int = 80 // 페이지당 결과 수 (1~80, 기본: 80)
    ): Response<KakaoImageSearchResponse>
}