package com.provenanceguard.vpn

import android.net.VpnService
import android.os.ParcelFileDescriptor
import java.io.FileInputStream

class GuardVpnService : VpnService() {

    private var vpn: ParcelFileDescriptor? = null

    fun start() {
        val builder = Builder()
        builder.addAddress("10.0.0.2", 24)
        builder.addRoute("0.0.0.0", 0)

        vpn = builder.establish()

        val input = FileInputStream(vpn!!.fileDescriptor)

        Thread {
            val buffer = ByteArray(32767)
            while (true) {
                val len = input.read(buffer)
                if (len > 0) {
                    PacketProcessor.process(buffer.copyOf(len))
                }
            }
        }.start()
    }
}