package com.provenanceguard.core.udp

data class UdpPacket(val srcPort: Int, val dstPort: Int)

object UdpParser {

    fun parse(payload: ByteArray): UdpPacket {
        val src = ((payload[0].toInt() and 0xFF) shl 8) or (payload[1].toInt() and 0xFF)
        val dst = ((payload[2].toInt() and 0xFF) shl 8) or (payload[3].toInt() and 0xFF)
        return UdpPacket(src, dst)
    }
}