package step4

fun main() {
    for (i in 1..10) {
        print(i)
    }

    println()

    val cakes = listOf("carrot", "cheese", "chocolate")

    for (cake in cakes) {
        println("Yummy, it's a $cake cake!")
    }
}
