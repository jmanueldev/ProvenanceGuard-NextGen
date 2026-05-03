package com.provenanceguard.core.risk

import com.provenanceguard.core.features.Features

object RiskEngine {

    fun score(f: Features): Float {
        return (f.netCount * 0.3f +
                f.uniqueTargets * 0.4f +
                f.density * 0.3f)
    }
}