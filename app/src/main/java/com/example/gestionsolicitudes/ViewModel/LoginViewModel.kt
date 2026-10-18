package com.example.gestionsolicitudes.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val correo: String = "",
    val clave: String = "",
    val correoError: String? = null,
    val claveError: String? = null,
    val errorGeneral: String? = null,
    val loginExitoso: Boolean = false
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onCorreoChange(valor: String) {
        _uiState.update { it.copy(correo = valor, correoError = null, errorGeneral = null) }
    }

    fun onClaveChange(valor: String) {
        _uiState.update { it.copy(clave = valor, claveError = null, errorGeneral = null) }
    }

    fun login() {
        val estado = _uiState.value

        val correoError = if (estado.correo.isBlank()) "El correo es obligatorio" else null
        val claveError = if (estado.clave.isBlank()) "La contraseña es obligatoria" else null

        if (correoError != null || claveError != null) {
            _uiState.update { it.copy(correoError = correoError, claveError = claveError) }
            return
        }

        // Cuenta ficticia para la demostración
        val valido = estado.correo.trim().lowercase() == "cliente@demo.cl" && estado.clave == "1234"

        if (valido) {
            _uiState.update { it.copy(loginExitoso = true) }
        } else {
            _uiState.update { it.copy(errorGeneral = "Correo o contraseña incorrectos") }
        }
    }

    fun loginConsumido() {
        _uiState.update { it.copy(loginExitoso = false) }
    }
}