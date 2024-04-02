.PHONY: help build build-local up down logs ps test ## Makefile 도움말
.DEFAULT_GOAL := help

DOCKER_TAG := latest
build: ## 배포용 도커 이미지 빌드
	docker build -t budougumi0617/gotodo:${DOCKER_TAG} \
		--target deploy ./

build-local: ## 로컬 환경용 도커 이미지 빌드
	docker compose build --no-cache

up: ## 자동 새로고침을 사용한 도커 구성기 실행
	docker compose up -d

down: ## 도커 구성기 종료
	docker compose down

logs: ## 도커 구성기 로그 출력
	docker compose logs -f

ps: ## 컨테이너 상태 확인
	docker compose ps

test: ## 테스트 실행
	go test -race -shuffle=on ./...

help: ## 욥션 보기
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | \
		awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'

# make 파일 너무 좋잖아?!