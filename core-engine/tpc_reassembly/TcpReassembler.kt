package com.provenanceguard.core.tcp

class TcpReassembler {

    private val map = mutableMapOf<String, MutableList<ByteArray>>()

    fun add(id: String, data: ByteArray) {
        map.getOrPut(id) { mutableListOf() }.add(data)
    }

    fun rebuild(id: String): ByteArray {
        return map[id]?.reduce { a, b -> a + b } ?: byteArrayOf()
    }
}