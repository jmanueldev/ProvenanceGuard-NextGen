package com.provenanceguard.vpn

import com.provenanceguard.core.packet.IpParser
import com.provenanceguard.core.graph.ProvenanceGraph

object PacketProcessor {

    fun process(raw: ByteArray) {
        val pkt = IpParser.parse(raw)

        ProvenanceGraph.add(
            from = pkt.src,
            to = pkt.dst,
            action = "NET"
        )
    }
}
