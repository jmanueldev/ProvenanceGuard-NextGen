package com.provenanceguard.core.graph

data class Edge(
    val from: String,
    val to: String,
    val action: String,
    val ts: Long = System.currentTimeMillis()
)

object ProvenanceGraph {

    private val edges = ArrayDeque<Edge>()

    fun add(from: String, to: String, action: String) {
        edges.add(Edge(from, to, action))
        prune()
    }

    fun snapshot(): List<Edge> = edges.toList()

    private fun prune() {
        val cutoff = System.currentTimeMillis() - 240_000
        while (edges.isNotEmpty() && edges.first().ts < cutoff) {
            edges.removeFirst()
        }
    }
}