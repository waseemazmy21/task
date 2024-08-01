package com.example.task.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {
    private val _tabBarState: MutableStateFlow<TabBarState> = MutableStateFlow(TabBarState())
    val tabBarState: StateFlow<TabBarState> = _tabBarState.asStateFlow()

    fun updateCurrentTabBarIndex(index: Int) {
        _tabBarState.update { currentState ->
            currentState.copy(currentTabIndex = index)
        }
    }


    companion object {
        val tabs = listOf("sign in", "sign up")
    }
}