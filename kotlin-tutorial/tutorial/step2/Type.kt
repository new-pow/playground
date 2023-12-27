package step2

fun main() {
    var customers = 10

    // Type.kt:4:21: warning: variable 'customers' initializer is redundant
    //    var customers = 10
    //                    ^
    // 쓸모없는 초기화 경고가 있음 oh!

    customers = 9

    customers = customers + 3
    customers += 7
    customers -= 2
    customers *= 3
    customers /= 2

    println(customers)
}

// unsigned integer 가 뭐지?
// unsigned integer 는 부호가 없는 정수를 의미한다.
// unsigned integer 는 부호가 없기 때문에 음수를 표현할 수 없다.
// 즉, 음수를 표현하지 않는 정수를 의미한다.
// unsigned integer 는 0 이상의 정수를 표현할 수 있다.

// UByte, UShort, UInt, ULong 이 있다.
// UByte 는 0 ~ 255 까지의 값을 표현할 수 있다.
// UShort 는 0 ~ 65535 까지의 값을 표현할 수 있다.
// UInt 는 0 ~ 4294967295 까지의 값을 표현할 수 있다.
// ULong 는 0 ~ 18446744073709551615 까지의 값을 표현할 수 있다.
