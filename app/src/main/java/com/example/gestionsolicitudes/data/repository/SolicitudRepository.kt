
package com.example.gestionsolicitudes.data.repository

import com.example.gestionsolicitudes.data.local.SolicitudDao
import com.example.gestionsolicitudes.data.local.SolicitudEntity
import kotlinx.coroutines.flow.Flow

class SolicitudRepository(
    private val solicitudDao: SolicitudDao
) {
    fun obtenerSolicitudes(): Flow<List<SolicitudEntity>> {
        return solicitudDao.obtenerSolicitudes()
    }

    suspend fun insertarSolicitud(solicitud: SolicitudEntity) {
        solicitudDao.insertarSolicitud(solicitud)
    }

    suspend fun actualizarSolicitud(solicitud: SolicitudEntity) {
        solicitudDao.actualizarSolicitud(solicitud)
    }

    suspend fun eliminarSolicitud(solicitud: SolicitudEntity) {
        solicitudDao.eliminarSolicitud(solicitud)
    }
}
