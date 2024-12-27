package com.mogun.imagesearch.ui.search

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mogun.imagesearch.ui.common.ImageCard

@Composable
fun SearchScreen() {
    Surface(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(10) {
                ImageCard()
            }
        }
    }
}