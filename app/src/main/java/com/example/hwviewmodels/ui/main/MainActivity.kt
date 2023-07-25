package com.example.hwviewmodels.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hwviewmodels.data.Routes
import com.example.hwviewmodels.domain.CustomObjectViewModel
import com.example.hwviewmodels.domain.CustomScreen
import com.example.hwviewmodels.domain.ImageHorizontalPager
import com.example.hwviewmodels.ui.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<CustomObjectViewModel>()

        setContent {
            CustomTheme {
                val navController = rememberNavController()
                Box(modifier = Modifier.fillMaxSize()){
                    NavHost(navController = navController, startDestination = "/"){
                        composable(Routes.first){ ImageHorizontalPager(navController = navController) }
                        composable(Routes.second) {CustomScreen(viewModel)}
                    }
                }
            }

        }
    }
}