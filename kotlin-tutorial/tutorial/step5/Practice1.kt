package step5

import kotlin.math.PI

fun circleArea(x : Int) {
    return println(PI * x * x)
}

fun main() {
    circleArea(2)
    println(circleArea2(2))
    println(intervalInSeconds(1, 1, 1))
    println(intervalInSeconds(minute = 1, second = 25))
    println(intervalInSeconds(hour = 2))
}

fun circleArea2(radius: Int): Double = PI * radius * radius

fun intervalInSeconds (hour: Int=0, minute: Int=0, second: Int=0) =
    ((hour * 3600) + minute) * 60 + second
