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

/**
 * Screen for displaying the list of members for a given party.
 * @param party The party to display the members for.
 * @param onMemberClick The function to be called when a member is clicked.
 * @param onBack The function to be called when the back button is pressed.
 * @param viewModel The view model for the app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberListScreen(
    party: String,
    onMemberClick: (Int) -> Unit,
    onBack: () -> Unit,
    viewModel: EduskuntaViewModel,
    modifier: Modifier = Modifier
) {
    /**
     * State of the members.
     */
    val members by viewModel.getMembersByParty(party).collectAsState(initial = emptyList())

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
            items(members) { member ->
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
