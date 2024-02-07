package com.example.lab4.api
import coil.compose.rememberImagePainter
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.lab4.dto.Champion

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ChampionImage(champion: Champion, modifier: Modifier = Modifier) {
    val imageUrl = "https://ddragon.leagueoflegends.com/cdn/12.6.1/img/champion/${champion.image.full}"
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "Image of ${champion.name}",
        modifier = modifier
    )
}
