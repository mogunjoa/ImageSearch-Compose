package com.mogun.imagesearch.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.CachePolicy
import com.mogun.imagesearch.data.ImageDocument
import androidx.compose.runtime.getValue
import com.mogun.imagesearch.R

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
            NetworkImageWithState(
                imageDocument.imageUrl
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

@Composable
fun NetworkImageWithState(
    imageUrl: String,
    contentDescription: String = "Network Image"
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true) // 부드러운 전환 효과
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.DISABLED)
            .build()
    )

    val painterState by painter.state.collectAsState()

    when (painterState) {
        is AsyncImagePainter.State.Loading -> {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .aspectRatio(1f),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(30.dp))
            }
        }
        is AsyncImagePainter.State.Error -> {
            Image(
                painter = painterResource(id = R.drawable.baseline_error_24),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
        is AsyncImagePainter.State.Success -> {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
        else -> {
            Text("Unknown state")
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