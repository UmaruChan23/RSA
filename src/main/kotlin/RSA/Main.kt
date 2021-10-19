package RSA

import RSA.keys.KeyPair

fun main() {
    val keygen = KeyPair(128)
    val code = keygen.getPublicKey().encrypt("RSA is shit".toByteArray(Charsets.UTF_8))
    println(keygen.getPrivateKey().decrypt(code).toString(Charsets.UTF_8))
    println("p: ${keygen.getP()}")
    println("q: ${keygen.getQ()}")
    println("n: ${keygen.getN()}")
    println("fi: ${keygen.getFi()}")
    println("e: ${keygen.getE()}")
    println("d: ${keygen.getD()}")
}