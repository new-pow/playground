package step3

fun main() {
    // Read-only map
    val readOnlyShapes = mapOf("triangle" to 3, "circle" to 0, "rectangle" to 4)
    println(readOnlyShapes)

    // Mutable map
    val shapes : MutableMap<String, Int> = mutableMapOf("triangle" to 3, "circle" to 0, "rectangle" to 4)
    println(shapes)

    val shapesLocked : Map<String, Int> = shapes
    println(shapesLocked)

    // 요소 접근
    println("the first shape is ${shapes["triangle"]}")
    // value로 접근할 때는 get() 메서드를 사용할 수도 있다.
    println("the first shape is ${shapes.get("triangle")}")

    // 숫자 세기
    println("the list has ${shapes.count()} elements")

    // 포함 관계
    println("circle" in shapes)
    println("circle" !in shapes)

    // 요소 추가 삭제
    shapes["square"] = 4 // 맨 뒤에 추가
    shapes.put("pentagon", 5) // 맨 뒤에 추가
    println(shapes)

    shapes.remove("circle") // 요소로 삭제
    println(shapes)

    shapes.remove("circle")
    println(shapes)

    // keys
    println(shapes.keys)
    // values
    println(shapes.values)

    // 요소 순회
    for (entry in shapes) {
        println("${entry.key} has ${entry.value} sides")
    }
}
