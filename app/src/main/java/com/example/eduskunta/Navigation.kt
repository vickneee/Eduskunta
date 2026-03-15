package com.example.eduskunta

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eduskunta.ui.screens.MainScreen
import com.example.eduskunta.ui.screens.MemberDetailScreen
import com.example.eduskunta.ui.screens.MemberListScreen
import com.example.eduskunta.ui.screens.PartyListScreen
import com.example.eduskunta.ui.viewmodel.EduskuntaViewModel

enum class Screen(@StringRes val title: Int) {
    MainScreen(title = R.string.app_name),
    PartyList(title = R.string.party_list_title),
    MemberList(title = R.string.member_list_title),
    MemberDetail(title = R.string.member_detail_title)
}

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    val context = LocalContext.current
    val app = context.applicationContext as EduskuntaApplication
    val viewModel: EduskuntaViewModel = viewModel(
        factory = EduskuntaViewModel.provideFactory(app.repository)
    )

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.name,
        modifier = Modifier.padding(16.dp)
    ) {
        composable(route = Screen.MainScreen.name) {
            MainScreen(
                viewModel = viewModel,
                onMainClick = {
                    navController.navigate(Screen.PartyList.name)
                }
            )
        }

        // Screen 2 - Party List Screen
        composable(route = Screen.PartyList.name) {
            PartyListScreen(
                viewModel = viewModel,
                onPartyClick = { party ->
                    navController.navigate("${Screen.MemberList.name}/$party")
                },
                onBack = { navController.navigateUp() }
            )
        }

        // Screen 3 - Members by party
        composable(route = "${Screen.MemberList.name}/{party}") { backStackEntry ->
            val party = backStackEntry.arguments?.getString("party") ?: ""
            MemberListScreen(
                viewModel = viewModel,
                party = party,
                onMemberClick = { personNumber ->
                    navController.navigate("${Screen.MemberDetail.name}/$personNumber")
                },
                onBack = { navController.navigateUp() }
            )
        }

        // Screen 4 - Member Detail Screen
        composable(route = "${Screen.MemberDetail.name}/{personNumber}") { backStackEntry ->
            val personNumber =
                backStackEntry.arguments?.getString("personNumber")?.toIntOrNull() ?: 0
            MemberDetailScreen(
                viewModel = viewModel,
                personNumber = personNumber,
                onBack = { navController.navigateUp() }
            )
        }
    }
}
