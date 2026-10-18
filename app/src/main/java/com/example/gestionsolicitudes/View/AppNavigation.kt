package com.example.gestionsolicitudes.View

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(
                onLoginOk = {
                    navController.navigate("menu") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("menu") {
            MenuScreen(
                onVerSolicitudes = { navController.navigate("lista") },
                onNuevaSolicitud = { navController.navigate("form") },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo("menu") { inclusive = true }
                    }
                }
            )
        }

        composable("lista") { Text("Lista de solicitudes (pendiente)") }
        composable("form") { Text("Formulario (pendiente)") }
    }
}