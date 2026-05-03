package com.provenanceguard.slm

import com.provenanceguard.core.features.Features

object PromptEngine {

    fun build(features: Features): String {
        return """
        Analyze behavior:
        NetCount=${features.netCount}
        UniqueTargets=${features.uniqueTargets}
        Density=${features.density}
        Is this suspicious?
        """.trimIndent()
    }
}