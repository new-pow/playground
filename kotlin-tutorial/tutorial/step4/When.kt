package step4

fun main() {
    val obj = "Hello"

    when (obj) {
        "Hello" -> println("Hi")
        "Goodbye" -> println("Bye")
        else -> println("What?")
    }

    val result = when(obj) {
        "Hello" -> "Greeting"
        "Bye" -> "Good bye"
        else -> "Unknown"
    }

    println(result)
    
    val temp = 10
    
    val description = when {
        temp < 0 -> "very cold"
        temp < 10 -> "cold"
        temp < 20 -> "cool"
        temp < 30 -> "warm"
        else -> "hot"
    }

    println(description)
}
