
package com.example.gestionsolicitudes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SolicitudEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun solicitudDao(): SolicitudDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun obtenerBaseDeDatos(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "solicitudes_db"
                ).build()

                INSTANCE = instancia
                instancia
            }
        }
    }
}
