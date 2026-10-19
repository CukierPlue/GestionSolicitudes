package com.example.gestionsolicitudes.ui

import com.example.gestionsolicitudes.data.local.SolicitudEntity

data class SolicitudUiState(
    val solicitudes: List<SolicitudEntity> = emptyList(),
    val cargando: Boolean = false,
    val mensajeError: String? = null
)