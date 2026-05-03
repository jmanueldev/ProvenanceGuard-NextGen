// core-engine/reputation/DomainReputation.kt
object DomainReputation {

    private val scores = mutableMapOf<String, Float>()

    fun update(domain: String, risk: Float) {
        val current = scores.getOrDefault(domain, 0.5f)
        scores[domain] = (current + risk) / 2
    }

    fun get(domain: String): Float {
        return scores.getOrDefault(domain, 0.5f)
    }
}

val domainRisk = c2Score * 0.6f + mlScore * 0.4f
DomainReputation.update(domain, domainRisk)