package com.provenanceguard.graph

import com.provenanceguard.core.graph.ProvenanceGraph
import com.provenanceguard.core.features.FeatureExtractor

object GraphManager {

    fun getFeatures() =
        FeatureExtractor.extract(ProvenanceGraph.snapshot())
}