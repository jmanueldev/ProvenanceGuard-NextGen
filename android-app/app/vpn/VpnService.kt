package com.provenanceguard.vpn

import android.net.VpnService
import android.os.ParcelFileDescriptor
import java.io.FileInputStream

class GuardVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null

    fun startVpn() {
        val builder = Builder()
        builder.addAddress("10.0.0.2", 24)
        builder.addRoute("0.0.0.0", 0)

        vpnInterface = builder.setSession("ProvenanceGuard").establish()

        val input = FileInputStream(vpnInterface!!.fileDescriptor)

        Thread {
            val buffer = ByteArray(32767)
            while (true) {
                val length = input.read(buffer)
                if (length > 0) {
                    PacketProcessor.process(buffer.copyOf(length))
                }
            }
        }.start()
    }
}