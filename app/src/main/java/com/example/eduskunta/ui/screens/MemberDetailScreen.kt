package com.example.eduskunta.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.eduskunta.ui.viewmodel.EduskuntaViewModel

/**
 * Screen for displaying the detail of a member.
 * @param personNumber The person number of the member.
 * @param viewModel The view model for the app.
 * @param onBack The function to be called when the back button is pressed.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberDetailScreen(
    personNumber: Int,
    viewModel: EduskuntaViewModel,
    onBack: () -> Unit
) {
    /**
     * State of the member and notes.
     */
    val member by viewModel.getMember(personNumber).collectAsState(initial = null)
    val notes by viewModel.getNotes(personNumber).collectAsState(emptyList())
    var noteText by remember { mutableStateOf("") }
    var isPositive by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(member?.let { "${it.first} ${it.last}" } ?: "") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    ) { paddingValues ->
        if (member == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues).padding(bottom = 36.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            val currentMember = member!!
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 48.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    val imageUrl =
                        "https://users.metropolia.fi/~peterh/edustajakuvat/${currentMember.last}-${currentMember.first}-web-${currentMember.personNumber}.jpg"
                            .replace("ä", "a")
                            .replace("ö", "o")
                            .replace("å", "a")
                            .replace("Ä", "A")
                            .replace("Ö", "O")
                            .replace("Å", "A")

                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "${currentMember.first} ${currentMember.last}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Fit,
                        placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                        error = painterResource(id = android.R.drawable.ic_menu_gallery)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "${currentMember.first} ${currentMember.last}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Istumapaikka: ${currentMember.seatNumber}")
                    Text(text = "Puolue: ${currentMember.party.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }}")
                    Text(text = "Vaalipiiri: ${currentMember.constituency}")
                    Text(text = "Syntynyt: ${currentMember.bornYear}")
                    if (currentMember.minister) {
                        Text(text = "Ministeri ✓", color = MaterialTheme.colorScheme.primary)
                    }
                    if (currentMember.twitter.isNotEmpty()) {
                        Text(text = "Twitter: @${currentMember.twitter}")
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Lisää muistiinpano", style = MaterialTheme.typography.titleMedium)
                    
                    OutlinedTextField(
                        value = noteText,
                        onValueChange = { noteText = it },
                        label = { Text("Muistiinpano") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(modifier = Modifier.padding(vertical = 8.dp)) {
                        Button(
                            onClick = { isPositive = true },
                            colors = if (isPositive)
                                ButtonDefaults.buttonColors(containerColor = Color.Green)
                            else
                                ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.outline)
                        ) { Text("+") }

                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                        Button(
                            onClick = { isPositive = false },
                            colors = if (!isPositive)
                                ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                            else
                                ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.outline)
                        ) { Text("-") }
                        
                        Spacer(modifier = Modifier.weight(1f))
                        
                        Button(onClick = {
                            if (noteText.isNotBlank()) {
                                viewModel.addNote(personNumber, noteText, isPositive)
                                noteText = ""
                            }
                        }) {
                            Text("Tallenna")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Muistiinpanot", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(notes) { note ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp, top = 10.dp, bottom = 20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = if (note.positive) "+" else "-",
                                    color = if (note.positive) Color.Green else MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                                Text(text = note.date, style = MaterialTheme.typography.bodySmall)
                            }
                            Text(text = note.text)
                        }
                    }
                }
            }
        }
    }
}
