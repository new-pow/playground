# 사다리 게임 연습
## 구현할 사항
### 할 일
- [x] 게임을 할 사람 인원을 입력 받는다.
- [x] 사다리를 몇 개 만들 것인지 입력 받는다.
- [x] 사다리를 라인이 랜덤으로 형성될 수 있도록 한다. -> `Random` 사용함
- [x] 사다리를 출력한다.

```text
참여할 사람은 몇 명인가요?
5
최대 사다리 높이는 몇 개인가요?
3
|-| | |-|-|
|-|-|-|-|-|
| |-|-|-|-|
```

- [x] 사다리 플레이어 이름을 입력 받을 수 있도록 한다. (최대 5글자, `,`로 구분)
- [x] 사다리를 출력할 때 플레이어 이름도 같이 출력한다.
- [x] 사다리를 출력할 때 사다리 폭도 넓어진다.
- [x] 사다리 라인이 겹치지 않도록 한다.

### 궁금한 것
#### 2단계
- [x] 코틀린에서 Collection을 어떻게 사용하지?
    - [x] [참고링크](https://kotlinlang.org/docs/collections-overview.html)
    - ![](https://kotlinlang.org/docs/images/collections-diagram.png)
- [x] 코틀린에서 `List`와 `Array`의 차이는 무엇인가?
    - [x] [참고링크](https://stackoverflow.com/questions/33727869/what-is-the-difference-between-array-and-list-in-kotlin)
- [x] null safe
    - [x] [참고링크](https://kotlinlang.org/docs/null-safety.html)
    - [x] [참고링크 safe calls](https://kotlinlang.org/docs/null-safety.html#safe-calls)
    - `?`,`!!`, `?.`, `?:` 등의 연산자를 사용한다.
- [ ] 단위 테스트는 어떻게 해야하지?
```kotlin
class LadderTest {
    @Test
    fun `사다리_높이가_0이면_에러를_반환한다`() {
        val ladder = Ladder(0, 5)
        assertThat(ladder.height).isEqualTo(0)
    }
}
```

#### 1단계
- [x] 패키지 단위로 build를 어떻게 하지?
```text
kotlinc YourFile.kt -include-runtime -d YourOutput.jar # 단일 파일 빌드
kotlinc src -include-runtime -d YourOutput.jar # 프로젝트 빌드
```
![](https://3553248446-files.gitbook.io/~/files/v0/b/gitbook-legacy-files/o/assets%2F-M5HOStxvx-Jr0fqZhyW%2F-MM0j1ZIMxf5jSpuWV-F%2F-MM0jIrie0U6EVQ38RQQ%2F01fig01_alt.jpg?alt=media&token=09784519-c4ec-4854-8728-9d0c50869cd0)

- [x] kotlin에서 입력을 어떻게 받지?
```kotlin
fun main() {
    val input = readLine() // 입력을 받는다.
    println(input) // 입력을 출력한다.
}
```

- [x] 왜 fun 에서 파라미터에 `val`나 `var`를 붙이면 에러가 나지?
    - `val`과 `var`는 변수 선언 시에만 사용할 수 있다. 함수의 파라미터는 함수 내부에서만 사용하는 불변 변수이기 때문에 `val`과 `var`를 붙일 수 없다.
    - [참고링크](https://stackoverflow.com/questions/68822461/why-is-var-or-val-not-allowed-in-a-functions-parameter-in-kotlin)
      - The main reason is that this was confusing: people tend to think that this means passing a parameter by reference, which we do not support (it is costly at runtime). Another source of confusion is primary constructors: “val” or “var” in a constructor declaration means something different from the same thing if a function declarations (namely, it creates a property). Also, we all know that mutating parameters is no good style, so writing “val” or “var” infront of a parameter in a function, catch block of for-loop is no longer allowed.
      - 해석 : `val`이나 `var`를 파라미터에 붙이면 참조로 파라미터를 전달하는 것으로 오해할 수 있기 때문에 사용하지 않는다. 또한, 파라미터를 변경하는 것은 좋은 스타일이 아니기 때문에 `val`이나 `var`를 사용하지 않는다.
