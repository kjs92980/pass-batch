.PHONY: docker-up docker-down

docker-up:
	docker-compose up -d --force-recreate

docker-down:
	docker-compose down -v