import kotlin.random.Random

data class Employee(val name: String, var salary: Int)

// To test your code, you need a generator that can create random employees.
// Define a class with a fixed list of potential names (inside the class body),
// and that is configured by a minimum and maximum salary (inside the class header).
//
// Once again, the main function demonstrates how you can use this class.

fun main() {
    val empGen = RandomEmployeeGenerator(10, 30)
    println(empGen.generateEmployee())
    println(empGen.generateEmployee())
    println(empGen.generateEmployee())
    empGen.minSalary = 50
    empGen.maxSalary = 100
    println(empGen.generateEmployee())
}

class RandomEmployeeGenerator(var minSalary : Int, var maxSalary : Int) {
    val names = listOf("John", "Jane", "Mary", "James", "Patricia", "Robert")
    fun generateEmployee() : Employee {
        val name = names[Random.nextInt(names.size)]
        val salary = Random.nextInt(minSalary, maxSalary)
        return Employee(name, salary)
    }
}
