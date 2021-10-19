package RSA.keys

import RSA.keys.RSAUtils.modPowByte
import java.math.BigInteger

class PrivateKey(private val d: BigInteger,
                 private val n: BigInteger
)
{
    fun decrypt(cipherText: ByteArray): ByteArray {
        return modPowByte(cipherText, d, n)
    }
}