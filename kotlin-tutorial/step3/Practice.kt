package step3

fun main() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)

    println("${greenNumbers.count() + redNumbers.count()}")

    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP", "SMTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("Support for $requested: $isSupported")

    val number2word = mapOf(1 to "a", 2 to "b", 3 to "c") // Write your code here
    val n = 2
    println("$n is spelt as '${number2word[n]}'")
}
