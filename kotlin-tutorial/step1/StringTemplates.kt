package step1


fun main() {
    val customers = 10
    println("The number of customers is $customers customers.") // 변수만 사용할 때
    println("The number of customers is ${customers + 2} customers.") // 변수와 연산자를 사용할 때

    println()
    val name = "Mary"
    val age = 20
    println("$name is $age years old.")
}
