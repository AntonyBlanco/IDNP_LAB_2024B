package com.ablancotunsa.samplebroadcastreceptor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.io.IOException
import java.io.OutputStreamWriter

class MoonBroadcastReceiver : BroadcastReceiver() {
    private val TAG = "MoonBroadcastReceiver"
    //val EXTRA_MOON_PHASE = "org.ablancotunsa.samplebroadcastreceptor.MoonBroadcastReceiver.EXTRA_MOON_PHASE"
    val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra(EXTRA_MESSAGE)?:""
        //writeToFile(message, context)
        Log.d(TAG, message)
    }

    fun writeToFile(data: String, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_APPEND))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString())
        }
    }

    companion object {
        const val EXTRA_MOON_PHASE = "org.ablancotunsa.samplebroadcastreceptor.MoonBroadcastReceiver.EXTRA_MOON_PHASE"
    }
}