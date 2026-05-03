fun isTlsHandshake(payload: ByteArray): Boolean {
    return payload.isNotEmpty() && payload[0] == 0x16.toByte()
}
fun buildJA3(
    version: Int,
    ciphers: List<Int>,
    extensions: List<Int>
): String {
    return "$version," +
           ciphers.joinToString("-") + "," +
           extensions.joinToString("-")
}
import java.security.MessageDigest

fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return md.digest(input.toByteArray())
        .joinToString("") { "%02x".format(it) }
}
val knownBad = setOf(
    "e7d705a3286e19ea42f587b344ee6865"
)

if (fingerprint in knownBad) {
    alert("Known malware TLS fingerprint")
}