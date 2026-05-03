package com.provenanceguard.core.features

import com.provenanceguard.core.graph.Edge

data class Features(
    val netCount: Int,
    val uniqueTargets: Int,
    val density: Float
)

object FeatureExtractor {

    fun extract(edges: List<Edge>): Features {

        val targets = mutableSetOf<String>()
        var netCount = 0

        for (e in edges) {
            if (e.action == "NET") {
                netCount++
                targets.add(e.to)
            }
        }

        return Features(
            netCount,
            targets.size,
            edges.size / 50f
        )
    }
}