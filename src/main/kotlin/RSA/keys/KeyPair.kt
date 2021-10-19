package RSA.keys

import java.math.BigInteger
import java.util.*

class KeyPair(private val bitLength: Int)
{
    /**
     * 309369537790349280842894344118131045037
     * 253984748677479328374250459070995412929
    */
    private val p: BigInteger = BigInteger.probablePrime(bitLength, Random())
    private val q: BigInteger = BigInteger.probablePrime(bitLength, Random())
    private val n: BigInteger = p.multiply(q)
    private val fi: BigInteger = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))
    private val e = generatePublicExponent()
    private val d: BigInteger = e.modInverse(fi)
    private val publicKey = PublicKey(e, n)
    private val privateKey = PrivateKey(d, n)

    fun getP() = p
    fun getQ() = q
    fun getN() = n
    fun getFi() = fi
    fun getE() = e
    fun getD() = d
    fun getPublicKey() = publicKey
    fun getPrivateKey() = privateKey

    private fun generatePublicExponent(): BigInteger {
        while (true) {
            val random = Random()
            val length: Int = bitLength + random.nextInt(fi.bitLength() - bitLength)
            val exponent = BigInteger(length, Random())
            if (exponent.compareTo(BigInteger.ONE) != 0 && exponent.compareTo(fi) == -1 && exponent.gcd(fi)
                    .compareTo(BigInteger.ONE) == 0
            ) return exponent
        }
    }
}