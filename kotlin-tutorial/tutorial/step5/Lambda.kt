package step5

fun main() {
    println(uppercaseString("hello"))
    println({ str: String -> str.uppercase() }("hello")) //  lambda without parameters, then there is no need to use `->`.

    val lowerCaseString = { str: String -> str.lowercase() }
    println(lowerCaseString("HELLO"))

    //filter
    val numbers = listOf(1, 2, 3, 4, 5)
    val evenNumbers = numbers.filter { it % 2 == 0 }
    val oddNumbers = numbers.filter { it % 2 == 1 }
    println(evenNumbers)
    println(oddNumbers)

    // map
    val doubledNumbers = numbers.map { it -> it * 2 }
    println(doubledNumbers)

    // total
    val total: (Int, Int) -> Int = { x, y -> x + y }
    println(total(1, 2))

    // toSeconds
    val timesInMinutes = listOf(2, 10, 15, 1)
    val min2Sec = toSeconds("minute") // 함수를 변수에 저장해둠?
    val totalTimeInSec = timesInMinutes.map(min2Sec).sum() // map 함수에 min2Sec 함수를 넣음
    println(totalTimeInSec)

    println(listOf(1,2,3).fold(0, {x, item -> x + item}))
    println(listOf(1,2,3).fold(0) {x, item -> x + item})

    // practice1
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/book-info"
    val id = 5
    val urls = actions.map { action -> "$prefix/$id/$action"}
    println(urls)

    //repeatN
    repeatN(5) {println("Hello!")}
}

fun uppercaseString(str: String): String {
    return str.uppercase()
}

fun toSeconds(time: String): (Int) -> Int = when (time) {
    "hour" -> { value -> value * 60 * 60 }
    "minute" -> { value -> value * 60 }
    "second" -> { value -> value }
    else -> { value -> value }
}

fun repeatN(n: Int, action: () -> Unit) {
    for (i in 1..n) {
        action()
    }
}
