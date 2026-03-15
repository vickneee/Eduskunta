package com.example.eduskunta.ui.screens

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eduskunta.R
import com.example.eduskunta.ui.viewmodel.EduskuntaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: EduskuntaViewModel,
    onMainClick: () -> Unit
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Suomen Eduskunta",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                )
                Text(
                    text = "🇫🇮",
                    fontSize = 64.sp,
                    modifier = Modifier.padding(top = 16.dp, start = 164.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_account_balance_24),
                    contentDescription = "Parliament",
                    modifier = Modifier
                        .size(180.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Button(onClick = onMainClick) {
                    Text(text = "Katso kansanedustajat")
                }
            }
        }
    }
}