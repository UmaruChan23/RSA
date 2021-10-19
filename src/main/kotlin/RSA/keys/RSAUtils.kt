package RSA.keys

import java.lang.reflect.Field
import java.math.BigInteger

object RSAUtils {

    private var bigIntegerDataField: Field? = null

    fun modPowByte(arg: ByteArray, e: BigInteger, n: BigInteger): ByteArray {
        val source = BigInteger(1, arg)
        val result = source.modPow(e, n)
        println(result)
        hideBigInteger(source)
        return getBytesWithoutSign(result)
    }

    private fun getBytesWithoutSign(arg: BigInteger): ByteArray {
        val sourceArray = arg.toByteArray()
        return if (sourceArray[0].equals(0)) {
            sourceArray
        } else {
            val withoutSign = ByteArray(sourceArray.size)
            System.arraycopy(sourceArray, 0, withoutSign, 0, withoutSign.size)
            withoutSign
        }
    }

    fun hideBigInteger(source: BigInteger) {
        try {
            if (bigIntegerDataField == null) {
                bigIntegerDataField = BigInteger::class.java.getDeclaredField("mag")
                bigIntegerDataField?.isAccessible = true
            }
            val mag = bigIntegerDataField!![source] as IntArray
            for (i in mag.indices) {
                mag[i] = 0
            }
        } catch (e: NoSuchFieldException) {
            System.err.println("[Warning] Parts of the plaintext may remain in memory")
        } catch (e: IllegalAccessException) {
            System.err.println("[Warning] Parts of the plaintext may remain in memory")
        }
    }
}