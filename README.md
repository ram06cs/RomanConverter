
# RomanConverter

RomanConverter is a Spring Boot application that converts numbers to Roman numerals. This project includes various components such as Docker, Elasticsearch, Grafana, Kibana, Logstash, and OpenTelemetry for monitoring and logging purposes.

## Table of Contents
- [Project Overview](#Project Overview)
- [Getting Started](#Getting Started)
    - [Prerequisites](#Getting Started)
    - [Cloning the Repository](#Getting Started)
    - [Installation](#Getting Started) 
- [Accessing the Services](#monitoring-and-logging)
- [Running Test Suite](#running-tests)
- [Author](#contributing)
- [References](#acknowledgments)


## **Project Overview**
  
The RomanConverter project utilizes several components for robust functionality, monitoring, and logging. Below are concise descriptions of each component:

**Spring Boot Application**

    Description: java application responsible for converting numbers to Roman numerals

    Role: Provides RESTful endpoints for number to roman numeral conversion

    Key Features:
        Provides a REST API
        Implements error handling
        Integrates with monitoring and logging tools

**Docker**

    Description: Containerization platform used to encapsulate the entire application stack

    Role: Ensures consistency across different environments and simplifies deployment

    Key Features:
        Docker Compose for orchestration
        Isolated containers for each service



**Logging Architecture**

![Spring ELK Image](screenshots/spring_elk_image.png)


**Logstash**

    Description: Server-side data processing pipeline

    Role: Ingests, transforms, and forwards logs to Elasticsearch

    Key Features:
        Centralized logging
        Data transformation and enrichment


**Elasticsearch**

    Description: Distributed search and analytics engine

    Role: Stores and indexes logs and metrics

    Key Features:
        Fast search and analysis
        Real-time indexing

**Kibana**

    Description: Data visualization dashboard for Elasticsearch

    Role: Visualizes and explores logs and metrics stored in Elasticsearch

    Key Features:
        Customizable dashboards
        Real-time visualization


**Metrics Architecture**

![spring_actutator_grafana.png](screenshots/spring_actutator_grafana.png)

**Prometheus**

    Description: Open-source systems monitoring and alerting toolkit

    Role: Prometheus scrapes metrics from below 

    Spring Boot Actuator:
    Endpoint: http://localhost:8080/actuator/prometheus
    Metrics include application JVM metrics, HTTP request statistics

    OpenTelemetry:
    Endpoint: http://localhost:4317
    Metrics include CPU usage, Memory usage and other performance metrics
    
    Key Features:
      Efficient storage and retrieval of time-series data
      Powerful query language (PromQL) for querying metrics
      Time series collection via pull model over HTTP

**Monitoring Architecture**

![otel-grafana.png](screenshots/otel-grafana.png)

**OpenTelemetry**

    Description: Opensource Framework for observability and collecting metrics

    Role: Exports application metrics for comprehensive observability

    Key Features:
        Unified observability framework
        Support for multiple backends
        Easily integrates with existing monitoring tools

**Grafana**

    Description: Monitoring and observability platform

    Role: Queries, visualizes, and alerts on metrics from multiple sources

    Key Features:
        Customizable dashboards
        Integration with various data sources
        Alerting and notifications



## **Getting Started**

## **Prerequisites**
Ensure that the following software is installed on your system and environment variables are set
   - Java 17 or later
   - Apache Maven 3.9.4 or later
   - Docker version 26.1.0-rd or later
   - Docker Compose version v2.27.1 or later
    
   ```sh
   java -version
   mvn -v
   docker version
   docker-compose version
   ```

Additionally, make sure that the following ports are not used by other applications and are available:

    9200, 9300 (Elasticsearch)
    5000, 9600 (Logstash)
    5601 (Kibana)
    8080 (Spring Boot Application)
    9090 (Prometheus)
    3000 (Grafana)
    4317, 13133, 8889 (OpenTelemetry Collector)

This ensures that there are no port conflicts when starting the services.

### **Installation Steps**


1. Clone the Repository 
    ```sh
    git clone https://github.com/ram06cs/RomanConverter
    cd RomanConverter
    ```
2. Build the Springboot Application

   Perform a Maven build inside the project directory to generate JAR files and dependencies:

    ```sh
    cd RomanConverter
    mvn clean install -U
    ```

3. Navigate to the docker directory

   Once the build is successful, Navigate to the `docker` directory.

     ```sh
    cd RomanConverter
    mvn clean install -U
    ```

      
4. Start the services using docker-compose
    
   ````sh
    docker-compose up -d --build
   ````
    
    The command would create following containers:

   ````sh
    [+] Running 8/8
    ✔ Network docker_default                      Created  0.1s
    ✔ Container docker-elasticsearch-1            Started  1.0s
    ✔ Container docker-prometheus-1               Started  1.0s
    ✔ Container docker-opentelemetry-collector-1  Started  1.7s
    ✔ Container docker-logstash-1                 Started  1.6s
    ✔ Container docker-kibana-1                   Started  1.3s
    ✔ Container docker-grafana-1                  Started  1.7s
    ✔ Container docker-springbootapp-1            Started  1.9s
   ````

5. Verify if all the services are up and running

   You should see an output similar to this:

    ```sh
    docker-compose ps
    ```

   Example output:

    ```plaintext
    rambala-macbook-pro:docker rambala$ docker-compose ps
    NAME                               IMAGE                                                  COMMAND                  SERVICE                   CREATED         STATUS                   PORTS
    docker-elasticsearch-1             docker.elastic.co/elasticsearch/elasticsearch:7.17.5   "/bin/tini -- /usr/l…"   elasticsearch             2 minutes ago   Up 2 minutes (healthy)   0.0.0.0:9200->9200/tcp, :::9200->9200/tcp, 0.0.0.0:9300->9300/tcp, :::9300->9300/tcp
    docker-grafana-1                   grafana/grafana:8.4.3                                  "/run.sh"                grafana                   2 minutes ago   Up 2 minutes             0.0.0.0:3000->3000/tcp, :::3000->3000/tcp
    docker-kibana-1                    docker.elastic.co/kibana/kibana:7.17.5                 "/bin/tini -- /usr/l…"   kibana                    2 minutes ago   Up 2 minutes             0.0.0.0:5601->5601/tcp, :::5601->5601/tcp
    docker-logstash-1                  docker.elastic.co/logstash/logstash:7.17.5             "/usr/local/bin/dock…"   logstash                  2 minutes ago   Up 2 minutes             0.0.0.0:5000->5000/tcp, :::5000->5000/tcp, 0.0.0.0:9600->9600/tcp, :::9600->9600/tcp, 5044/tcp
    docker-opentelemetry-collector-1   otel/opentelemetry-collector:0.56.0                    "/otelcol --config /…"   opentelemetry-collector   2 minutes ago   Up 2 minutes             0.0.0.0:4317->4317/tcp, :::4317->4317/tcp, 0.0.0.0:8889->8889/tcp, :::8889->8889/tcp, 0.0.0.0:13133->13133/tcp, :::13133->13133/tcp, 55678-55679/tcp
    docker-prometheus-1                prom/prometheus:v2.33.5                                "/bin/prometheus --c…"   prometheus                2 minutes ago   Up 2 minutes             0.0.0.0:9090->9090/tcp, :::9090->9090/tcp
    docker-springbootapp-1             romanconverter/springbootapp                           "/bin/sh -c 'java -j…"   springbootapp             2 minutes ago   Up 2 minutes             0.0.0.0:8080->8080/tcp, :::8080->8080/tcp
    ```

   Allow a few minutes for all containers to start. Ensure no port conflicts to avoid issues. Verify the status with:

    ```sh
    docker-compose ps
    ```

   Repeat the command until all containers are up and running.



### Services and Ports

| Service                    | Port | Description                                       |
|----------------------------|------|---------------------------------------------------|
| Elasticsearch              | 9200 | HTTP port for Elasticsearch API access            |
| Elasticsearch              | 9300 | Transport port for node communication             |
| Logstash                   | 5000 | TCP port for receiving logs                       |
| Logstash                   | 9600 | HTTP port for monitoring API                      |
| Kibana                     | 5601 | HTTP port for accessing Kibana web interface      |
| Spring Boot Application    | 8080 | HTTP port for accessing the application           |
| Prometheus                 | 9090 | HTTP port for accessing Prometheus web interface  |
| Grafana                    | 3000 | HTTP port for accessing Grafana web interface     |
| OpenTelemetry Collector    | 4317 | gRPC port for receiving OTLP data                 |
| OpenTelemetry Collector    | 13133| HTTP port for health check endpoint               |
| OpenTelemetry Collector    | 8889 | HTTP port for Prometheus metrics exporter         |

## **Accessing the Services**

- **Spring Boot Application**: 
    - Spring Security is configured to secure the application endpoint (/romannumeral) 
    - Endpoints (/actuator and /swagger-ui) are configured without authentication for monitoring purposes.
    - Swagger: `http://localhost:8080/swagger-ui/index.html`
    - API Endpoint: `http://localhost:8080/romannumeral?query=1500` (credentials: `admin/Roman@123`)
    - Spring Actuator Metrics: 
      - `http://localhost:8080/actuator`
      - `http://localhost:8080/actuator/prometheus`
- **Prometheus**: `http://localhost:9090/targets`
- **Kibana**: `http://localhost:5601`
- **Elasticsearch**: `http://localhost:9200`
- **Logstash**: `http://localhost:9600` (Monitoring API)
- **Grafana**: `http://localhost:3000` (credentials: `admin/Hello@123`)


## **Devops - Metrics, Monitoring and Logging**

- **Metrics**: Metrics are collected using OpenTelemetry and exported to Prometheus, visualized in Grafana.
- **Logs**: Application logs are sent to Logstash and stored in Elasticsearch, visualized in Kibana.

## **Running Test Suite**

To ensure the application works as expected, follow these steps to run the test suite provided with the project.

1. **Run the Test Suite**

   To run the test suite, execute the following Maven command from the project root directory:

    ```sh
    mvn test
    ```

2. **Verify Test Results**

   After running the tests, Maven will display the results in the console. Detailed test results can also be found in the `target/surefire-reports` directory. The results are stored in XML and TXT files:

    ```sh
    ls target/surefire-reports
    ```

   The following files should be generated:

    ```plaintext
    TEST-org.romanconverter.RomanConverterApplicationTests.xml
    TEST-org.romanconverter.validator.ConversionValidatorTest.xml
    org.romanconverter.service.RomanConversionServiceTest.txt
    ```

3. **Generate HTML Test Reports**

   To generate HTML test reports, run the following command from the project root directory:

    ```sh
    mvn surefire-report:report-only
    ```

4. **View the HTML Test Report**

   The HTML report will be generated in the `target/site` directory. Open the file in a browser for a detailed view:

    ```sh
    open target/site/surefire-report.html
    ```


## **Author**
- **Ram Kumar Balasubramani** 


## **Project Structure**

```
RomanConverter
├── HELP.md
├── docker
│   ├── docker-compose.yml
│   ├── elasticsearch
│   │   ├── Dockerfile
│   │   └── elasticsearch.yml
│   ├── grafana
│   │   ├── Dockerfile
│   │   ├── config.ini
│   │   ├── dashboards
│   │   │   ├── opentelemetry_18812_jvm.json
│   │   │   └── springboot-stats-6756.json
│   │   └── provisioning
│   │       ├── dashboards
│   │       │   └── dashboards.yml
│   │       └── datasources
│   │           └── datasources.yml
│   ├── kibana
│   │   └── kibana.yml
│   ├── logstash
│   │   ├── Dockerfile
│   │   └── pipeline
│   │       └── logstash.conf
│   ├── opentelemetry-collector
│   │   ├── Dockerfile
│   │   ├── otel-collector-config-working.yml
│   │   └── otel-collector-config.yml
│   ├── prometheus
│   │   └── prometheus.yml
│   └── springboot
│       └── Dockerfile
├── logs
│   └── RomanConverter
│       └── app.log
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── romanconverter
│   │   │           ├── RomanConverterApplication.java
│   │   │           ├── config
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   └── SwaggerConfig.java
│   │   │           ├── controller
│   │   │           │   └── RomanConversionController.java
│   │   │           ├── enums
│   │   │           │   └── ErrorCodeEnum.java
│   │   │           ├── exception
│   │   │           │   ├── RomanConversionException.java
│   │   │           │   └── handler
│   │   │           │       └── RomanConversionControllerAdvice.java
│   │   │           ├── model
│   │   │           │   ├── ErrorInfo.java
│   │   │           │   └── RomanConversionResponse.java
│   │   │           ├── service
│   │   │           │   ├── ConversionService.java
│   │   │           │   └── impl
│   │   │           │       └── RomanConversionService.java
│   │   │           └── validator
│   │   │               └── RomanConversionValidator.java
│   │   └── resources
│   │       ├── application-test.properties
│   │       ├── application.properties
│   │       └── logback-spring.xml
│   └── test
│       ├── java
│       │   └── org
│       │       └── romanconverter
│       │           ├── RomanConverterApplicationTests.java
│       │           ├── config
│       │           │   └── TestSecurityConfig.java
│       │           ├── controller
│       │           │   └── RomanConversionControllerTest.java
│       │           ├── integration
│       │           │   └── ConversionIntegrationTest.java
│       │           ├── service
│       │           │   └── RomanConversionServiceTest.java
│       │           └── validator
│       │               └── ConversionValidatorTest.java
│       └── resources
│           └── logback-test.xml
└── target
```


## **References**

- Spring Boot
- Docker
- OpenTelemetry
- Grafana
- Prometheus
- Elasticsearch
- Kibana
- Logstash
