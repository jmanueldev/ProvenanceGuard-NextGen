// core-engine/c2/BeaconDetector.kt
class BeaconDetector {

    private val timestamps = mutableListOf<Long>()

    fun record(ts: Long) {
        timestamps.add(ts)
    }

    fun isBeaconing(): Boolean {
        if (timestamps.size < 5) return false

        val intervals = timestamps.zipWithNext { a, b -> b - a }
        val avg = intervals.average()

        val variance = intervals.map { (it - avg) * (it - avg) }.average()

        return variance < 1000  // low variance = periodic
    }
}