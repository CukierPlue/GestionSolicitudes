package com.example.gestionsolicitudes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestionsolicitudes.ui.ListaSolicitudesScreen
import com.example.gestionsolicitudes.ui.SolicitudFormScreen
import com.example.gestionsolicitudes.ui.SolicitudViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: SolicitudViewModel = viewModel()
                    val solicitudes by viewModel.listaSolicitudes.collectAsState(initial = emptyList())

                    var mostrarFormulario by remember { mutableStateOf(false) }

                    if (mostrarFormulario) {
                        SolicitudFormScreen(
                            onVolver = { mostrarFormulario = false },
                            onGuardar = { titulo, descripcion, categoria, servicio ->
                                viewModel.actualizarTitulo(titulo)
                                viewModel.actualizarDescripcion(descripcion)
                                viewModel.actualizarCategoria(categoria)
                                viewModel.guardarSolicitud()
                                mostrarFormulario = false
                            }
                        )
                    } else {
                        ListaSolicitudesScreen(
                            solicitudes = solicitudes,
                            onEliminarSolicitud = { solicitud -> viewModel.eliminarSolicitud(solicitud) },
                            onNuevaSolicitud = { mostrarFormulario = true }
                        )
                    }
                }
            }
        }
    }
}