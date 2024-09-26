package com.ablancotunsa.loginregistersample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ablancotunsa.loginregistersample.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    // Declaración correcta de view binding en Kotlin
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /*setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        // Habilitar ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Accedemos a la vista raíz con .root

        // Variables para el EditText y Button con ViewBinding
        val edtUsername = binding.edtUsername
        val edtPassword = binding.edtPassword
        val btnLogin = binding.btnLogin
        val btnAddAccount = binding.btnAddAccount

        // Listener para el botón de login
        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()

            if (username == "admin" && password == "admin") {
                Toast.makeText(applicationContext, "Bienvenido a mi App", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Bienvenido a mi App")
            } else {
                Toast.makeText(applicationContext, "Error en la autenticación", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Error en la autenticación")
            }
        }
        btnAddAccount.setOnClickListener {
            val intent = Intent(applicationContext, AccountActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}