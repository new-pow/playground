
fun hello() {
    return println("Hello") // ? return type null 인건가?
}

fun main() {
    hello()
    println(total(1,2))
}

fun total(x : Int, y : Int) : Int {
    return x+y
}
