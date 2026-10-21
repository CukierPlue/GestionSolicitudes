
package com.example.gestionsolicitudes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestionsolicitudes.data.local.SolicitudEntity

@Composable
fun ListaSolicitudesScreen(
    solicitudes: List<SolicitudEntity>,
    onNuevaSolicitud: () -> Unit,
    onEliminarSolicitud: (SolicitudEntity) -> Unit
) {
    // Estado para controlar cuál solicitud se quiere eliminar (activa el diálogo)
    var solicitudAEliminar by remember { mutableStateOf<SolicitudEntity?>(null) }

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

                            Spacer(modifier = Modifier.padding(4.dp))

                            // Botón de eliminar que abre el diálogo de confirmación
                            Button(
                                onClick = { solicitudAEliminar = solicitud },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.errorContainer,
                                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Eliminar solicitud")
                            }
                        }
                    }
                }
            }
        }
    }

    // --- DIÁLOGO DE CONFIRMACIÓN DE ELIMINACIÓN (PUNTO 2) ---
    solicitudAEliminar?.let { solicitud ->
        AlertDialog(
            onDismissRequest = { solicitudAEliminar = null },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Estás seguro de eliminar la solicitud \"${solicitud.titulo}\"? Esta acción no se puede deshacer.") },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    onClick = {
                        onEliminarSolicitud(solicitud)
                        solicitudAEliminar = null
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { solicitudAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}