package com.example.gestionsolicitudes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestionsolicitudes.ui.ListaSolicitudesScreen
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

                    ListaSolicitudesScreen(
                        solicitudes = solicitudes,
                        onEliminarSolicitud = { solicitud -> viewModel.eliminarSolicitud(solicitud) },
                        onNuevaSolicitud = { /* Acción para nueva solicitud */ }
                    )
                }
            }
        }
    }
}