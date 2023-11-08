#!/usr/bin/env bash

podman run --rm --name cart-db -e POSTGRES_USER=supershop -e POSTGRES_PASSWORD=supershop -p 5432:5432 docker.io/library/postgres:15.4
