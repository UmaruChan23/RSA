package RSA.keys

import java.math.BigInteger

class PublicKey(private val e: BigInteger,
                private val n: BigInteger) {

    fun encrypt(text: ByteArray): ByteArray {
        val cipher = RSAUtils.modPowByte(text, e, n)
        for (i in text.indices) { text[i] = 0 }
        return cipher
    }
}