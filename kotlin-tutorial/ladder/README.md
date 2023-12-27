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

### 궁금한 것
#### 1단계
- [x] 패키지 단위로 build를 어떻게 하지?
```text
kotlinc YourFile.kt -include-runtime -d YourOutput.jar # 단일 파일 빌드
kotlinc src -include-runtime -d YourOutput.jar # 프로젝트 빌드
```

- [x] kotlin에서 입력을 어떻게 받지?
```kotlin
fun main() {
    val input = readLine() // 입력을 받는다.
    println(input) // 입력을 출력한다.
}
```
