package step1

val popcorn = 5 // read-only
const val hotdog = 7 // ? 왜 const를 쓰는가?
var customers = 10  // mutable

fun main() {
    customers = 8
    println(customers)
}

// We recommend that you declare all variables as read-only (val) by default.
// Declare mutable variables (var) only if necessary.
