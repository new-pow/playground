package step3

fun main() {
    // Read-only list
    val readOnlyShapes = listOf("circle", "triangle", "rectangle")
    println(readOnlyShapes)

    // Mutable list
    val shapes : MutableList<String> = mutableListOf("circle", "triangle", "rectangle")
    println(shapes)

    val shapesLocked : List<String> = shapes
    println(shapesLocked)

    // 요소 접근
    println("the first shape is ${shapes[0]}")

    // 첫번째, 마지막 요소 접근
    println("the first shape is ${shapes.first()} and the last one is ${shapes.last()}")

    // 숫자 세기
    println("the list has ${shapes.count()} elements")

    // 포함 관계
    println("circle" in shapes)
    println("circle" !in shapes)

    // 요소 추가 삭제
    shapes.add("square") // 맨 뒤에 추가
    println(shapes)

    shapes.add(0, "pentagon") // 맨 앞에 추가
    println(shapes)

    shapes.removeAt(1) // 인덱스로 삭제
    println(shapes)

    shapes.remove("circle") // 요소로 삭제
    println(shapes)
    // 만약 리스트에 중복된 요소가 있다면, 첫번째 요소만 삭제된다.

    // 만약 리스트에 없는 요소를 삭제하려고 하면 아무 일도 일어나지 않는다.\
    shapes.remove("circle")
    println(shapes)
}
