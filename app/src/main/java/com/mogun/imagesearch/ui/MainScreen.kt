@file:OptIn(ExperimentalMaterial3Api::class)

package com.mogun.imagesearch.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mogun.imagesearch.ui.favorite.FavoriteScreen
import com.mogun.imagesearch.ui.search.SearchScreen

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val viewModel = SearchViewModel()

    Scaffold(
        topBar = {
            MainHeader(viewModel)
        },
        bottomBar = {
            BottomNavigationBar(navHostController, currentRoute)
        }
    ) {
        NavHost(
            navController = navHostController,
            startDestination = BottomNavItem.Search.route,
            modifier = Modifier.padding(it)
        ) {
            composable(BottomNavItem.Search.route) { SearchScreen(viewModel) }
            composable(BottomNavItem.Favorite.route) { FavoriteScreen() }
        }
    }
}

@Composable
fun MainHeader(viewModel: SearchViewModel) {
    TopAppBar(
        title = {
            Text("모근조아의 이미지 검색")
        },
        actions = {
            IconButton(onClick = {
                viewModel.searchImages("강아지")
                println("검색 버튼 클릭")
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "검색")
            }
        }
    )
}

@Composable
fun BottomNavigationBar(navHostController: NavHostController, currentRoute: String?) {
    val items = listOf(
        BottomNavItem.Search,
        BottomNavItem.Favorite,
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navHostController.navigate(item.route) {
                        // 스택에 동일한 경로가 중복으로 추가되지 않도록 설정
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}

