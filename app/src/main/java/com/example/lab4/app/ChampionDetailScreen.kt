package com.example.lab4.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.lab4.api.Champion
import com.example.lab4.navigation.AppRouter
import com.example.lab4.navigation.ButtonHandler
import com.example.lab4.navigation.Screens

@Composable
fun ChampionDetailScreen(champion: Champion) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Name: ${champion.name}", style = MaterialTheme.typography.h6)
                Text("Title: ${champion.title}")
            }
            ChampionImage(champion = champion, modifier = Modifier.size(80.dp))
        }
        Text(
            "Blurb: ${champion.blurb}",
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text("Champion Info:", style = MaterialTheme.typography.subtitle1)
        Row {
            Text("Difficulty: ${champion.info.difficulty}", modifier = Modifier.weight(1f))
            Text("Attack: ${champion.info.attack}", modifier = Modifier.weight(1f))
            Text("Defense: ${champion.info.defense}", modifier = Modifier.weight(1f))
            Text("Magic: ${champion.info.magic}", modifier = Modifier.weight(1f))
        }
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text("Stats:", style = MaterialTheme.typography.subtitle1)
        Text("Hp: ${champion.stats.hp}")
        Text("Movespeed: ${champion.stats.movespeed}")
        Text("Armor: ${champion.stats.armor}")
        Text("Attack Range: ${champion.stats.attackrange}")
        Text("Hp Regen: ${champion.stats.hpregen}")
    }

}


