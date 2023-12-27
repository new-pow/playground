class Customer

// 괄호 안에 val 또는 var를 넣지 않고 프로퍼티를 선언할 수 있지만 인스턴스가 생성된 후에는 이러한 프로퍼티에 액세스할 수 없습니다.
class Contact(val id: Int, var email: String) { // class header
    val category: String = "Work"
    // We recommend that you declare properties as **read-only (val)**
    //  unless they need to be changed after an instance of the class is created.
    // 해석 : 클래스의 인스턴스가 생성된 후에 변경해야 하는 경우를 제외하고는 프로퍼티를 읽기 전용으로 선언하는 것을 권장합니다.

    fun printId() {
        println(id)
    }
}

class Membership (var grade: String = "BASIC") // default value를 지정할 수 있습니다.

fun main() {
    val contact = Contact(1, "i.newpow@gmail.com")
    println(contact.id)
    println(contact.email)
    println(contact.category)
    println(contact.toString()) // toString() 메소드를 오버라이드하지 않으면 클래스 이름과 인스턴스의 해시 코드가 반환됩니다.

    contact.printId()
}
