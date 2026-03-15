package com.example.eduskunta.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eduskunta.ui.viewmodel.EduskuntaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartyListScreen(
    onPartyClick: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: EduskuntaViewModel,
) {
    val members by viewModel.members.collectAsState()
    val parties = members.map { it.party }.distinct().sorted()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Puolueet") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    ) { paddingValues ->
        if (members.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        } else {
            LazyColumn(modifier = Modifier.padding(paddingValues).padding(horizontal = 24.dp)) {
                items(parties) { party ->
                    ListItem(
                        headlineContent = { Text(party) },
                        modifier = Modifier.clickable { onPartyClick(party) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
