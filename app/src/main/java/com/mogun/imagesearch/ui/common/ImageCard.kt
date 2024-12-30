package com.mogun.imagesearch.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mogun.imagesearch.R
import com.mogun.imagesearch.data.ImageDocument

@Composable
fun ImageCard(
    imageDocument: ImageDocument
) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        onClick = {
            println("이미지 클릭")
        }
    ) {
        Row (
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageDocument.imageUrl,
                contentDescription = "Network Image",
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.baseline_error_24),
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(imageDocument.displaySiteName)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    println("클릭했어염!")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "즐겨찾기",
                )
            }
        }
    }
}

@Preview
@Composable
fun ImageCardPreview() {
    Column(
        modifier = Modifier.background(Color.White)
    ) {

    }
}