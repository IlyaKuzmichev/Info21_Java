#!/usr/bin/env bash

docker-compose down

# shellcheck disable=SC2046
docker volume rm $(docker volume ls -q --filter name=postgres_data)

echo "Volumes removed successfully."