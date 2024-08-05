package com.example.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.TaskTheme
import com.example.task.ui.AppViewModel
import com.example.task.ui.sign_in.SignInScreen
import com.example.task.ui.sign_in.SignUpScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskTheme {
                App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    Scaffold(
        topBar = { AppBar() }, modifier = Modifier

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)

        ) {
            TabBar()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),

        title = {
            Text(
                "JOIN US",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 24.dp)
            )
        },

        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Localized description"
                )
            }
        }, modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(12.dp)
    )
}

@Composable
fun TabBar(appViewModel: AppViewModel = viewModel()) {
    val tabBarState by appViewModel.tabBarState.collectAsState()

    val tabs = AppViewModel.tabs

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = tabBarState.currentTabIndex,

            ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = (index == tabBarState.currentTabIndex),
                    onClick = { appViewModel.updateCurrentTabBarIndex(index) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = MaterialTheme.colorScheme.primary,
                    interactionSource = MutableInteractionSource(

                    ),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp)
                ) {
                    Text(text = tab, color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }

        when (tabBarState.currentTabIndex) {
            0 -> SignInScreen(
                onSignUpClick = { appViewModel.updateCurrentTabBarIndex(1) },
                modifier = Modifier.fillMaxSize()
            )

            1 -> SignUpScreen(
                onSignInClick = { appViewModel.updateCurrentTabBarIndex(0) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    TaskTheme {
        App()
    }
}