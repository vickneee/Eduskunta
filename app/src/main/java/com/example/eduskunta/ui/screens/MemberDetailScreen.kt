package com.example.eduskunta.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eduskunta.ui.viewmodel.EduskuntaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberDetailScreen(
    personNumber: Int,
    viewModel: EduskuntaViewModel,
    onBack: () -> Unit
) {
    val edustajat by viewModel.edustajat.collectAsState()
    val member = edustajat.find { it.personNumber == personNumber }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(member?.let { "${it.first} ${it.last}" } ?: "") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (member == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                item {
                    /**
                     * Photo from API
                     */
                    val imageUrl =
                        "https://users.metropolia.fi/~peterh/edustajakuvat/${member.last}-${member.first}-web-${member.personNumber}.jpg"
                            .replace("ä", "a")
                            .replace("ö", "o")
                            .replace("å", "a")
                            .replace("Ä", "A")
                            .replace("Ö", "O")
                            .replace("Å", "A")

                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "${member.first} ${member.last}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp),
                        contentScale = ContentScale.Fit,
                        placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                        error = painterResource(id = android.R.drawable.ic_menu_gallery)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Member info
                    Text(
                        text = "${member.first} ${member.last}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Puolue: ${member.party}")
                    Text(text = "Vaalipiiri: ${member.constituency}")
                    Text(text = "Syntynyt: ${member.bornYear}")
                    if (member.minister) {
                        Text(text = "Ministeri ✓", color = MaterialTheme.colorScheme.primary)
                    }
                    if (member.twitter.isNotEmpty()) {
                        Text(text = "Twitter: @${member.twitter}")
                    }
                }
            }
        }
    }
}