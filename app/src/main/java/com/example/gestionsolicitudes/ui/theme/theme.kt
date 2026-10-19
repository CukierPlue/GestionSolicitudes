
package com.example.gestionsolicitudes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColoresApp = lightColorScheme()

@Composable
fun GestionSolicitudesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColoresApp,
        content = content
    )
}
