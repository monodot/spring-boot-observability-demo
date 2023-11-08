# Spring Boot Observability Demo (with Grafana Cloud)

This repository contains a demo app that showcases how to configure a Java Spring Boot application to send metrics, logs and traces to Grafana Cloud (with OpenTelemetry).

## Prerequisites

- [Java 17](https://whichjdk.com/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/) or [Podman](https://podman.io/) (optional - but makes it easier to run the demo)

## Getting started, using Grafana Cloud

1. Clone this repository

1. Create a [Grafana Cloud](https://grafana.com/products/cloud/) account
 
1. Create a new [Grafana Cloud API key](https://grafana.com/docs/grafana-cloud/account-management/authentication-and-permissions/create-api-key/)

1. Bring up the app and the database with the included Compose file:

    ```
    export GRAFANA_CLOUD_API_KEY=xxxxx
    export LOKI_USERNAME=1111
    export TEMPO_USERNAME=2222
    export METRICS_USERNAME=3333

    docker-compose up -d
   
    # or, if you prefer podman
    podman-compose up -d
    ```
   
    When the app is starting up, you may see some errors from Spring Boot in the logs. This is normal and will go away once PostgreSQL has started up.

1.  Test the app by getting information about cart #1:

    ```
    curl http://localhost:8085/api/carts/1
    ```
