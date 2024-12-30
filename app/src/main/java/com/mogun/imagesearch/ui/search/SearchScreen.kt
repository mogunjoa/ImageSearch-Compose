package com.mogun.imagesearch.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mogun.imagesearch.ui.SearchViewModel
import com.mogun.imagesearch.ui.UiState
import com.mogun.imagesearch.ui.common.ImageCard

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    val uiState by viewModel.imageSearchState.collectAsState()

    Surface(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        when (uiState) {
            is UiState.Empty -> {
                Box(
                    modifier = Modifier.fillMaxSize(), // 화면 전체를 채움
                    contentAlignment = Alignment.Center // 자식 요소를 중앙 정렬
                ) {
                    Text("검색 결과가 없습니다.")
                }
            }
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), // 화면 전체를 채움
                    contentAlignment = Alignment.Center // 자식 요소를 중앙 정렬
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                val data = (uiState as UiState.Success).data
                LazyColumn(
                    modifier = Modifier.padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(data.documents.size) { index ->
                        ImageCard(data.documents[index])
                    }
                }
            }
            is UiState.Error -> {
                val errorMessage = (uiState as UiState.Error).message
                Text("Error: $errorMessage")
            }
        }
    }
}