# Alicación de Login para Android
 • Curso: Introducción al Desarrollo de Nuevas Plataformas
 • Estudiante: Blanco Trujillo Antony Jacob

## 1. Descripción General
Este repositorio contiene una actividad de inicio de sesión simple implementada en Kotlin para una aplicación de Android. Utiliza View Binding para un acceso más fácil a los elementos de la interfaz de usuario e implementa una lógica de autenticación básica.

## 2. Explicación del Código

### 2.1. Declaración del Paquete
```kotlin
package com.ablancotunsa.lab01_loginsample
```
 • Esta línea declara el nombre del paquete para el archivo Kotlin, organizando el código dentro de un espacio de nombres específico.

### 2.2. Importaciones
```kotlin
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ablancotunsa.lab01_loginsample.databinding.ActivityMainBinding
```
 • Estas líneas importan clases y funciones necesarias del marco de Android y otras bibliotecas.

### 2.3. Definición de la Clase MainActivity
```kotlin
class MainActivity : AppCompatActivity() {
```
 • Esta línea define la clase MainActivity, que hereda de AppCompatActivity.

### 2.4. Declaración de View Binding
```kotlin
private lateinit var binding: ActivityMainBinding
```
 • Esta línea declara una variable binding de tipo ActivityMainBinding que se inicializará más tarde.

### 2.5. Método onCreate
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
```
 • El método onCreate se sobrescribe para realizar la inicialización única cuando se crea la actividad.

### 2.6. Habilitar Visualización de Borde a Borde
```kotlin
enableEdgeToEdge(this)
```
 • Este método habilita la visualización de borde a borde, permitiendo que el contenido se extienda hasta los bordes de la pantalla.

### 2.7. Inicialización de View Binding
```kotlin
binding = ActivityMainBinding.inflate(layoutInflater) 
setContentView(binding.root) 
```
 • Se inicializa View Binding al inflar el diseño utilizando ActivityMainBinding.inflate().

### 2.8. Accediendo a Elementos de la Interfaz de Usuario
```kotlin
val edtUsername = binding.edtUsername 
val edtPassword = binding.edtPassword 
val btnLogin = binding.btnLogin 
```
 • Estas líneas recuperan referencias a los elementos de la interfaz de usuario utilizando View Binding.
### 2.9. Listener del Botón
```kotlin
btnLogin.setOnClickListener { 
    val username = edtUsername.text.toString() 
    val password = edtPassword.text.toString() 
```
 • Se establece un listener al botón de inicio de sesión para recuperar el texto ingresado en ambos campos EditText.
### 2.10. Lógica de Autenticación
```kotlin
if (username == "admin" && password == "admin") { 
    Toast.makeText(applicationContext, "Bienvenido a mi App", Toast.LENGTH_SHORT).show() 
    Log.d(TAG, "Bienvenido a mi App") 
} else { 
    Toast.makeText(applicationContext, "Error en la autenticación", Toast.LENGTH_SHORT).show() 
    Log.d(TAG, "Error en la autenticación") 
}
```
 • Este bloque verifica si tanto el nombre de usuario como la contraseña coinciden con valores predefinidos y muestra mensajes apropiados.
### 2.11. Objeto Compañero
```kotlin
companion object { 
    private const val TAG = "MainActivity" 
}
```
 • El objeto compañero permite definir miembros estáticos dentro de una clase.