package com.ablancotunsa.samplebroadcastemisor

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ablancotunsa.samplebroadcastemisor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "EmisorMainActivity"
    private val EXTRA_MOON_PHASE = "org.ablancotunsa.samplebroadcastreceptor.MoonBroadcastReceiver.EXTRA_MOON_PHASE"
    val EXTRA_MESSAGE = "EXTRA_MESSAGE"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnSendMessage = binding.btnSendMessage
        val edtMessage = binding.edtMessage

        btnSendMessage.setOnClickListener {
            val message = edtMessage.text.toString()
            sendMessage(message)
        }

    }

    fun sendMessage(message: String) {
        val intent = Intent()
        intent.setComponent(ComponentName(
            "com.ablancotunsa.samplebroadcastreceptor", // Nombre del paquete de la app receptora
            "com.ablancotunsa.samplebroadcastreceptor.MoonBroadcastReceiver" // Nombre completo de la clase del receptor
        ))

        intent.setAction(EXTRA_MOON_PHASE)
        intent.putExtra(EXTRA_MESSAGE, message)

        sendBroadcast(intent)
        Log.d(TAG,"Mensaje enviado")
    }
}