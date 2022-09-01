# -d: 백그라운드 실행, --force-recreate: 강제 재생성
db-up:
	docker-compose up -d --force-recreate

# -v: volume 삭제
db-down:
	docker-compose down -v