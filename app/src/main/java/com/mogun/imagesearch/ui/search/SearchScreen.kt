package com.mogun.imagesearch.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mogun.imagesearch.ui.MainActivity
import com.mogun.imagesearch.ui.SearchViewModel
import com.mogun.imagesearch.ui.UiState
import com.mogun.imagesearch.ui.common.ImageCard

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = viewModel(LocalContext.current as MainActivity)
    val uiState by viewModel.imageSearchState.collectAsState()
    var keyword by remember { mutableStateOf("") }
    var keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Column {
            SearchBox(
                keyword = keyword,
                onValueChange = { keyword = it },
                seachAction = {
                    viewModel.searchImages(keyword)
                    keyboardController?.hide()
                }
            )
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
}

@Composable
fun SearchBox(
    keyword: String,
    onValueChange: (String) -> Unit,
    seachAction: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = keyword,
            onValueChange = onValueChange,
            placeholder = { Text(text = "검색어를 입력해주세요") },
            shape = RoundedCornerShape(size = 8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = true
            ),
            keyboardActions = KeyboardActions(
                onSearch = { seachAction() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            maxLines = 1,
            singleLine = true,
            leadingIcon = { Icon(Icons.Filled.Search, "SearchIcon") }
        )
    }
}