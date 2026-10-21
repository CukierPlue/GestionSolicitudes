package com.example.gestionsolicitudes.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionsolicitudes.data.local.AppDatabase
import com.example.gestionsolicitudes.data.local.SolicitudEntity
import com.example.gestionsolicitudes.data.repository.SolicitudRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SolicitudViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SolicitudRepository

    val listaSolicitudes: StateFlow<List<SolicitudEntity>>

    var uiState by mutableStateOf(SolicitudUiState())
        private set

    init {
        val solicitudDao = AppDatabase.obtenerBaseDeDatos(application).solicitudDao()
        repository = SolicitudRepository(solicitudDao)
        listaSolicitudes = repository.obtenerSolicitudes().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    // Guardar o Actualizar solicitud
    fun guardarSolicitud() {
        viewModelScope.launch {
            val solicitud = SolicitudEntity(
                id = uiState.idSeleccionado,
                titulo = uiState.titulo,
                descripcion = uiState.descripcion,
                categoria = uiState.categoria,
                servicioRelacionado = uiState.servicioRelacionado,
                estado = uiState.estado,
                evidenciaUri = uiState.evidenciaUri
            )
            if (uiState.idSeleccionado == 0) {
                repository.insertarSolicitud(solicitud)
            } else {
                repository.actualizarSolicitud(solicitud)
            }
            limpiarFormulario()
        }
    }

    // Eliminar solicitud
    fun eliminarSolicitud(solicitud: SolicitudEntity) {
        viewModelScope.launch {
            repository.eliminarSolicitud(solicitud)
        }
    }

    // Preparar formulario para editar
    fun cargarSolicitudParaEditar(solicitud: SolicitudEntity) {
        uiState = uiState.copy(
            idSeleccionado = solicitud.id,
            titulo = solicitud.titulo,
            descripcion = solicitud.descripcion,
            categoria = solicitud.categoria,
            servicioRelacionado = solicitud.servicioRelacionado,
            estado = solicitud.estado,
            evidenciaUri = solicitud.evidenciaUri
        )
    }

    fun limpiarFormulario() {
        uiState = SolicitudUiState()
    }

    fun actualizarTitulo(nuevoTitulo: String) {
        uiState = uiState.copy(titulo = nuevoTitulo)
    }

    fun actualizarDescripcion(nuevaDescripcion: String) {
        uiState = uiState.copy(descripcion = nuevaDescripcion)
    }

    fun actualizarCategoria(nuevaCategoria: String) {
        uiState = uiState.copy(categoria = nuevaCategoria)
    }
}