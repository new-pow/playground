# 배포용 컨테이너에 포함시킬 바이너리 생성 컨테이너
FROM golang:1.18.2-bullseye as deploy-builder

WORKDIR /app

COPY go.mod go.sum ./
RUN go mod download

COPY . .
RUN go build -trimpath -ldflags "-s -w" -o /app

# ---------------------------------------------------------
# 배포용 컨테이너
FROM debian:bullseye-slim as deploy

RUN apt-get update

COPY --from=deploy-builder /app/app .

CMD ["./app"]

# ---------------------------------------------------------
# 로컬 개발 환경에서 사용하는 자동 새로고침 환경
FROM golang:1.22-alpine as dev
WORKDIR /app
# 오픈소스 라이브러리인 air 소스 코드 파일이 변경되면 자동으로 서버를 재시작해주는 도구
RUN go install github.com/cosmtrek/air@latest
COPY go.mod go.sum ./
RUN go mod download

CMD ["air", "-c", ".air.toml"]
