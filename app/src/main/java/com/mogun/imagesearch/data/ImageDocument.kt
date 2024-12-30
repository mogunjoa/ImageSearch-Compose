package com.mogun.imagesearch.data

import com.google.gson.annotations.SerializedName

data class KakaoImageSearchResponse(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("documents") val documents: List<ImageDocument>
)

data class Meta(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("is_end") val isEnd: Boolean
)

data class ImageDocument(
    @SerializedName("collection") val collection: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("display_sitename") val displaySiteName: String,
    @SerializedName("doc_url") val docUrl: String,
    @SerializedName("datetime") val datetime: String
)