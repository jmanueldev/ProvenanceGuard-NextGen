package com.provenanceguard.core.packet

data class IpPacket(
    val src: String,
    val dst: String,
    val protocol: Int,
    val payload: ByteArray
)

object IpParser {
    fun parse(raw: ByteArray): IpPacket {
        val protocol = raw[9].toInt()
        val src = raw.slice(12..15).joinToString(".") { it.toUByte().toString() }
        val dst = raw.slice(16..19).joinToString(".") { it.toUByte().toString() }
        return IpPacket(src, dst, protocol, raw.copyOfRange(20, raw.size))
    }
}