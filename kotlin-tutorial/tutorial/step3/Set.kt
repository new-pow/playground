package step3

fun main() {
    // Read-only set
    val readOnlyShapes = setOf("circle", "triangle", "rectangle")
    println(readOnlyShapes)

    // Mutable set
    val shapes : MutableSet<String> = mutableSetOf("circle", "triangle", "rectangle")
    println(shapes)

    val shapesLocked : Set<String> = shapes
    println(shapesLocked)

    // 요소 접근
    println("the first shape is ${shapes.first()}") // 왜 first()가 더 좋은가? -> 인덱스로 접근하는 것보다 더 효율적이다.
    println("the last shape is ${shapes.last()}") // 왜 set인데 first()와 last()가 있는가? -> 해당 메서드들이 Set이 상속받은 인터페이스인 Iterable에서 제공되기 때문
    println("the first shape is ${shapes.elementAt(0)}") // 인덱스로 접근

    // 숫자 세기
    println("the list has ${shapes.count()} elements")

    // 포함 관계
    println("circle" in shapes)
    println("circle" !in shapes)

    // 요소 추가 삭제
    shapes.add("square") // 맨 뒤에 추가
    println(shapes)

    shapes.remove("circle") // 요소로 삭제
    println(shapes)
    // 만약 셋에 중복된 요소가 있다면, 첫번째 요소만 삭제된다.

    // 만약 셋에 없는 요소를 삭제하려고 하면 아무 일도 일어나지 않는다.
    shapes.remove("circle")
    println(shapes)
}
