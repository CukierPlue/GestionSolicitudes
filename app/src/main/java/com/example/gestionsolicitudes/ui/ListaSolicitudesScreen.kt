
package com.example.gestionsolicitudes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestionsolicitudes.data.local.SolicitudEntity

@Composable
fun ListaSolicitudesScreen(
    solicitudes: List<SolicitudEntity>,
    onNuevaSolicitud: () -> Unit
    onEliminarSolicitud: (SolicitudEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Gestión de Solicitudes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = onNuevaSolicitud,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Nueva solicitud")
        }

        Spacer(modifier = Modifier.padding(8.dp))

        if (solicitudes.isEmpty()) {
            Text("Todavía no hay solicitudes registradas.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = solicitudes,
                    key = { it.id }
                ) { solicitud ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(all = 16.dp)
                        ) {
                            Text(
                                text = solicitud.titulo,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text("Descripción: ${solicitud.descripcion}")
                            Text("Categoría: ${solicitud.categoria}")
                            Text("Estado: ${solicitud.estado}")
                        }
                    }
                }
            }
        }
    }
}

s