package com.example.eduskunta.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import kotlin.text.replaceFirstChar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberListScreen(
    party: String,
    onMemberClick: (Int) -> Unit,
    onBack: () -> Unit,
    viewModel: EduskuntaViewModel,
    modifier: Modifier = Modifier
) {
    val members by viewModel.members.collectAsState()
    val filteredMembers = members.filter { it.party == party }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(party.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(horizontal = 34.dp)) {
            items(filteredMembers) { member ->
                ListItem(
                    headlineContent = { Text("${member.first} ${member.last}") },
                    supportingContent = { Text(member.constituency) },
                    modifier = Modifier.clickable { onMemberClick(member.personNumber) }
                )
                HorizontalDivider()
            }
        }
    }
}
