# Goroutine
- native thread와 goroutine은 M:N으로 매칭됨
- [참고링크](https://www.mimul.com/blog/go-vs-java-thread/)
- GC 지원 : 컴파일에 같이 컴파일됨

```
go func() {
    // do sth
}
```

# Go scheduler
- logical process가 go routine을 관리해줌
- local queue -> global queue -> network queue

# Channel
- type이 있는 데이터를 주고받을 수 있는 통로
    - int 채널, String 채널, 채널의 채널 등등
- 기본적으로 상대방 채널이 준비될때까지 block됨

# 오늘의 해볼 것
- n개의 작업을 goroutine으로 병렬 처리 가능
- goroutine과 channel의 준비사항

---
# 스크래핑 활용 아이디에이션
- 
