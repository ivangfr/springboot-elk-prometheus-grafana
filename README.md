# springboot-elk-prometheus-grafana

The goal of this project is to implement a [`Spring Boot`](https://docs.spring.io/spring-boot/index.html) application, called `movies-api`, and use [`Filebeat`](https://www.elastic.co/beats/filebeat) & `ELK Stack` ([`Elasticsearch`](https://www.elastic.co/elasticsearch), [`Logstash`](https://www.elastic.co/logstash) and [`Kibana`](https://www.elastic.co/kibana)) to collect and visualize application's **logs** and [`Prometheus`](https://prometheus.io/) & [`Grafana`](https://grafana.com/) to monitor application's **metrics**.

> **Note**: In [`kubernetes-minikube-environment`](https://github.com/ivangfr/kubernetes-minikube-environment/tree/master/movies-api-elk-prometheus-grafana) repository, it shows how to deploy this project in `Kubernetes` (`Minikube`)

## Proof-of-Concepts & Articles

On [ivangfr.github.io](https://ivangfr.github.io), I have compiled my Proof-of-Concepts (PoCs) and articles. You can easily search for the technology you are interested in by using the filter. Who knows, perhaps I have already implemented a PoC or written an article about what you are looking for.

## Additional Readings

- \[**Medium**\] [**Exposing Metrics of a Spring Boot API that uses Spring Data JPA and PostgreSQL**](https://medium.com/@ivangfr/exposing-metrics-of-a-spring-boot-api-that-uses-spring-data-jpa-and-postgresql-5ff188097b0f)
- \[**Medium**\] [**Running Prometheus and Grafana to monitor a Spring Boot API application**](https://medium.com/@ivangfr/running-prometheus-and-grafana-to-monitor-a-spring-boot-api-application-e6a3313563f2)

## Project Diagram

![project-diagram](documentation/project-diagram.jpeg)

## Application

- ### movies-api

  `Spring Boot` Web Java application that exposes a REST API for managing movies. It provides the following endpoints:
  ```text
    POST /api/movies -d {"imdbId","title","year","genre","country"}
     GET /api/movies
     GET /api/movies/{imdbId}
  DELETE /api/movies/{imdbId}
  ```

## Prerequisites

- [`Java 21`](https://www.oracle.com/java/technologies/downloads/#java21) or higher;
- A containerization tool (e.g., [`Docker`](https://www.docker.com), [`Podman`](https://podman.io), etc.)

## Start Environment

- Open a terminal and inside the `springboot-elk-prometheus-grafana` root folder run:
  ```bash
  docker compose up -d
  ```

- Wait for the Docker containers to be up and running. To check it, run:
  ```bash
  docker ps -a
  ```

## Running application with Maven

- Open a terminal and make sure you are inside the `springboot-elk-prometheus-grafana` folder;

- Run the following command:
  ```bash
  ./mvnw clean spring-boot:run --projects movies-api
  ```
  > **Note**: If you want to switch to the "non-json-logs" profile (which may be useful during development), run:
  > ```bash
  > ./mvnw clean spring-boot:run --projects movies-api -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=non-json-logs"
  > ```

## Running application as Docker container

- ### Build Docker image

  - In a terminal, make sure you are inside the `springboot-elk-prometheus-grafana` root folder;
  - Run the following script to build the image:
    - JVM
      ```bash
      ./build-docker-images.sh
      ```
    - Native
      ```bash
      ./build-docker-images.sh native
      ```

- ### Environment variables

  | Environment Variable | Description                                                       |
  |----------------------|-------------------------------------------------------------------|
  | `MYSQL_HOST`         | Specify host of the `MySQL` database to use (default `localhost`) |
  | `MYSQL_PORT`         | Specify port of the `MySQL` database to use (default `3306`)      |

- ### Start Docker container

  - In a terminal, run the following command to start the Docker container:
    ```bash
    docker run --rm --name movies-api -p 8080:8080 \
      -e MYSQL_HOST=mysql \
      --network=springboot-elk-prometheus-grafana_default \
      ivanfranchin/movies-api:1.0.0
    ```
    > **Note**: If you want to change to "non-json-logs", add `-e SPRING_PROFILES_ACTIVE=non-json-logs` to the command above

## Calling the Application Endpoints

- **Create movie**

  ```bash
  curl -X POST http://localhost:8080/api/movies \
    -H "Content-Type: application/json" \
    -d '{"imdbId": "tt5580036", "title": "I, Tonya", "year": 2017, "genre": "Biography", "country": "USA"}'
  ```

- **Get all movies**

  ```bash
  curl http://localhost:8080/api/movies
  ```
- **Get movie**

  ```bash
  curl http://localhost:8080/api/movies/tt5580036
  ```

- **Delete movie**

  ```bash
  curl -X DELETE http://localhost:8080/api/movies/tt5580036
  ```

## Services URLs

- **MySQL**

  ```bash
  docker exec -it -e MYSQL_PWD=secret mysql mysql -uroot --database moviesdb
  SELECT * FROM movies;
  ```
  > Type `exit` to leave the `MySQL monitor`

- **Prometheus**

  `Prometheus` can be accessed at http://localhost:9090

  ![prometheus](documentation/prometheus.jpeg)

- **Grafana**

  `Grafana` can be accessed at http://localhost:3000

  - In order to login, type `admin` for both `username` and `password`.
  - You can skip the next screen that asks you to provide a new password.
  - Select `Dashboards` on the left-menu.
  - Click `movies-api-dashboard`.

  ![grafana](documentation/movies-api-grafana-dashboard.jpeg)

- **Kibana**

  `Kibana` can be accessed at http://localhost:5601

  > **Note**: in order to see movies-api logs in Kibana, you must run the application as Docker container

  _Configuration_

  - Access `Kibana` website;
  - Click `Explore on my own`.
  - On the main page, click the _"burger"_ menu icon, then click `Discover`.
  - Click `Create index pattern` button.
  - In the `Create index pattern` form:
    - Set `filebeat-*` fot the `Name` field;
    - Select `@timestamp` for the `Timestamp field` combo-box.
    - Click `Create index pattern` button.
  - Click the _"burger"_ menu icon again, and then click `Discover` to start performing searches.
  
  ![kibana](documentation/kibana.jpeg)

- **Elasticsearch**

  `Elasticsearch` URL is http://localhost:9200

  _Useful queries_
  ```text
  # Check it's up and running
  curl localhost:9200
  
  # Check indexes
  curl "localhost:9200/_cat/indices?v"
  
  # Check filebeat index mapping
  curl "localhost:9200/filebeat-*/_mapping"
  
  # Simple search
  curl "localhost:9200/filebeat-*/_search?pretty"
  ```

## Shutdown

- To stop the application, go to the terminal where it is running and press `Ctrl+C`.
- To stop and remove docker compose containers, network, and volumes, go to a terminal and, inside the `springboot-elk-prometheus-grafana` root folder, run the following command:
  ```bash
  docker compose down -v
  ```

## Cleanup

To remove the Docker images created by this project, go to a terminal and, inside the `springboot-elk-prometheus-grafana` root folder, run the script below:
```bash
./remove-docker-images.sh
```

## References

https://medium.com/@sece.cosmin/docker-logs-with-elastic-stack-elk-filebeat-50e2b20a27c6
