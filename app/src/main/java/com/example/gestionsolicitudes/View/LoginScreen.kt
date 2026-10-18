package com.example.gestionsolicitudes.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestionsolicitudes.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginOk: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state.loginExitoso) {
        if (state.loginExitoso) {
            viewModel.loginConsumido()
            onLoginOk()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Gestión de Solicitudes", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = state.correo,
            onValueChange = viewModel::onCorreoChange,
            label = { Text("Correo") },
            isError = state.correoError != null,
            supportingText = { Text(state.correoError ?: "") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.clave,
            onValueChange = viewModel::onClaveChange,
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            isError = state.claveError != null,
            supportingText = { Text(state.claveError ?: "") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (state.errorGeneral != null) {
            Text(
                text = state.errorGeneral!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(8.dp))
        }

        Spacer(Modifier.height(8.dp))
        Button(onClick = viewModel::login, modifier = Modifier.fillMaxWidth()) {
            Text("Ingresar")
        }
    }
}