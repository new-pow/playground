data class User (val name: String, val id: Int) // data class

// The most useful predefined member functions of data classes are:
// - equals()
// - hashCode()
// - toString()
// - copy()

fun main() {
    val user = User("Iirin", 1)
    println(user)
    println(user.name)
    println(user.id)

    val secondUser = User("Another", 2)
    val thirdUser = User("Another", 2)
    println(secondUser)

    println("user == secondUser: ${user == secondUser}") // data class는 내용이 다르면 false를 반환합니다.
    println("secondUser == thirdUser: ${secondUser == thirdUser}") // data class는 내용이 같으면 true를 반환합니다.

    println(user.copy())
    println(user.copy("Hi"))
    println(user.copy(id = 3))

}
