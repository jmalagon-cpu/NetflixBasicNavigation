package me.fabiansuarez.compose.lab.netflixbasicnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.HomeScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.LoginScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterScreen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep2Screen
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep3Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            NetflixApp()
        }
    }
}

@Composable
fun NetflixApp() {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate("home") {

                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register_step1")
                }
            )
        }

        composable("home") {
            HomeScreen()
        }

        composable("register_step1") {
            RegisterScreen(
                onNextClick = { navController.navigate("register_step2") }
            )
        }

        composable("register_step2") {
            RegisterStep2Screen(
                onNextClick = { navController.navigate("register_step3") }
            )
        }

        composable("register_step3") {
            RegisterStep3Screen(
                onFinishClick = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
    }
}