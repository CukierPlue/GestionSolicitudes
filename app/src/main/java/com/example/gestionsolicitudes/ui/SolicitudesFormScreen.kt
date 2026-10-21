package com.example.gestionsolicitudes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SolicitudFormScreen(
    onGuardar: (String, String, String, String) -> Unit,
    onVolver: () -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var servicioRelacionado by remember { mutableStateOf("") }

    // Estados para controlar si se intentó guardar y mostrar errores (Punto 3)
    var errorIntentado by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Nueva solicitud",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título *") },
            isError = errorIntentado && titulo.isBlank(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (errorIntentado && titulo.isBlank()) {
            Text(
                text = "El título es obligatorio.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción *") },
            isError = errorIntentado && descripcion.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorIntentado && descripcion.isBlank()) {
            Text(
                text = "La descripción es obligatoria.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoría *") },
            isError = errorIntentado && categoria.isBlank(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (errorIntentado && categoria.isBlank()) {
            Text(
                text = "La categoría es obligatoria.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = servicioRelacionado,
            onValueChange = { servicioRelacionado = it },
            label = { Text("Servicio relacionado (Opcional)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                errorIntentado = true
                if (titulo.isNotBlank() && descripcion.isNotBlank() && categoria.isNotBlank()) {
                    onGuardar(
                        titulo.trim(),
                        descripcion.trim(),
                        categoria.trim(),
                        servicioRelacionado.trim()
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar solicitud")
        }

        OutlinedButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}