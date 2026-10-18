
package com.example.gestionsolicitudes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SolicitudDao {

    @Query("SELECT * FROM solicitudes ORDER BY id DESC")
    fun obtenerSolicitudes(): Flow<List<SolicitudEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarSolicitud(solicitud: SolicitudEntity)

    @Update
    suspend fun actualizarSolicitud(solicitud: SolicitudEntity)

    @Delete
    suspend fun eliminarSolicitud(solicitud: SolicitudEntity)
}
