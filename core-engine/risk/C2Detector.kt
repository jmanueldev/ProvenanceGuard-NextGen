// core-engine/risk/C2Detector.kt
class C2Detector {

    fun score(
        beacon: Boolean,
        suspiciousDomain: Boolean,
        entropy: Boolean
    ): Float {

        var score = 0f
        if (beacon) score += 0.4f
        if (suspiciousDomain) score += 0.3f
        if (entropy) score += 0.3f

        return score
    }
}