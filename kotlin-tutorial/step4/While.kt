package step4

fun main() {
    var cakesEaten = 0;

    while (cakesEaten < 3) {
        println("Eat a cake")
        cakesEaten ++
    }

    println()

    var cakesBaked = 0

    while (cakesEaten < 5) {
        println("Eat again a cake")
        cakesEaten ++
    }
    do {
        println("Bake a cake")
        cakesBaked ++
    } while (cakesBaked < cakesEaten)
}
