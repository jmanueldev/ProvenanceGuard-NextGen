package com.provenanceguard.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.provenanceguard.graph.GraphManager

class DetectionService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread {
            while (true) {
                val features = GraphManager.getFeatures()
                println("Features: $features")
                Thread.sleep(5000)
            }
        }.start()

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}