package com.example.rickymortychallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickymortychallenge.api.RickyManager
import com.example.rickymortychallenge.api.model.Character
import com.example.rickymortychallenge.ui.theme.RickyMortyChallengeTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import coil3.compose.rememberAsyncImagePainter
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickyMortyChallengeTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    val rickyManager = RickyManager()
                    App( modifier = Modifier.padding(innerPadding), rickyManager)

                }
            }
        }
    }
}


@Composable
fun App(modifier: Modifier = Modifier, rickyManager: RickyManager) {
    val characters by rickyManager.rickyResponse

    Scaffold(

    ) { padding ->
        Box(modifier = modifier.padding(padding)) {
            if (characters.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            } else {
                CharacterList(characters)
            }
        }
    }
}

@Composable
fun CharacterList(characters: List<com.example.rickymortychallenge.api.model.Character>) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(characters) { character ->
            CharacterItem(character)
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Load character image
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = "Character Image",
                modifier = Modifier.size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                character.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = "Species: ${character.species}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Gender: ${character.gender}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Origin: ${character.origin?.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

