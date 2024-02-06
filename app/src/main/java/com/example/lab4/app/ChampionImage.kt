package com.example.lab4.app
import coil.compose.rememberImagePainter
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.example.lab4.api.Champion

@Composable
fun ChampionImage(champion: Champion, modifier: Modifier = Modifier) {
    val imageUrl = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/${champion.id}_0.jpg"
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "Image of ${champion.name}",
        modifier = modifier
    )
}
