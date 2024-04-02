package main

import (
	"context"
	"fmt"
	"io"
	"net"
	"net/http"
	"testing"

	"golang.org/x/sync/errgroup"
)

func TestRun(t *testing.T) {
	l, err := net.Listen("tcp", "localhost:0") // 0으로 지정하면 사용 가능한 포트 번호를 동적으로 선택한다.
	if err != nil {
		t.Fatalf("failed to listen: %v", err)
	}

	ctx, cancel := context.WithCancel(context.Background())
	eg, ctx := errgroup.WithContext(ctx)
	eg.Go(func() error {
		return run(ctx, l)
	})
	in := "message"
	// 어떤 포트 번호로 리슨하고 있는지 확인
	url := fmt.Sprintf("http://%s/%s", l.Addr().String(), in)
	t.Logf("try request to %q", url)
	rsp, err := http.Get(url)
	if err != nil {
		t.Errorf("failed to get: %+v", err)
	}
	defer rsp.Body.Close()
	got, err := io.ReadAll(rsp.Body)
	if err != nil {
		t.Fatalf("failed to read body: %v", err)
	}
	want := fmt.Sprintf("Hello, %s!", in)
	if string(got) != want {
		t.Errorf("want %q, but got %q", want, got)
	}
	cancel()
	if err := eg.Wait(); err != nil {
		t.Fatal(err)
	}
}
