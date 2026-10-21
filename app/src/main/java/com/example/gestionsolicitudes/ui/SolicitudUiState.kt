package com.example.gestionsolicitudes.ui

import com.example.gestionsolicitudes.data.local.SolicitudEntity

data class SolicitudUiState(
    val idSeleccionado: Int = 0,
    val titulo: String = "",
    val descripcion: String = "",
    val categoria: String = "",
    val servicioRelacionado: String = "",
    val estado: String = "Recibida",
    val evidenciaUri: String? = null,

    val solicitudes: List<SolicitudEntity> = emptyList(),
    val cargando: Boolean = false,
    val mensajeError: String? = null
)