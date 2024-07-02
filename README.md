
# RomanConverter

RomanConverter is a Spring Boot application that converts numbers to Roman numerals. This project includes various components such as Docker, Elasticsearch, Grafana, Kibana, Logstash, and OpenTelemetry for monitoring and logging purposes.

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

## **Prerequisites**

- Docker
- Docker Compose
- Java 17+
- Maven

## **Getting Started**

### **Clone the Repository**

```sh
git clone https://github.com/your-repo/RomanConverter.git
cd RomanConverter
```

### **Build the Application**

```sh
./mvnw clean package
```

### **Running the Application with Docker Compose**

1. Navigate to the `docker` directory.

```sh
cd docker
```

2. Start the services using Docker Compose.

```sh
docker-compose up --build
```

## **Services**

The Docker Compose setup includes the following services:

- **Elasticsearch**: For storing application logs.
- **Grafana**: For visualizing metrics.
- **Kibana**: For viewing and analyzing logs.
- **Logstash**: For processing logs.
- **OpenTelemetry Collector**: For collecting and exporting metrics and traces.
- **Prometheus**: For scraping metrics from OpenTelemetry and providing data to Grafana.
- **Spring Boot Application**: The main application providing the Roman numeral conversion service.

## **Accessing the Services**

- **Application**: `http://localhost:8080`
- **Grafana**: `http://localhost:3000` (Default credentials: `admin/admin`)
- **Kibana**: `http://localhost:5601`
- **Prometheus**: `http://localhost:9090`
- **Jaeger**: `http://localhost:16686` (if using Jaeger for traces)

## **Endpoints**

- **Conversion Endpoint**: `http://localhost:8080/convert` (POST request with JSON body)
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **Actuator**: `http://localhost:8080/actuator`

## **Monitoring and Logging**

- **Metrics**: Metrics are collected using OpenTelemetry and exported to Prometheus, visualized in Grafana.
- **Logs**: Application logs are sent to Logstash and stored in Elasticsearch, visualized in Kibana.

## **Configuration Files**

- **Elasticsearch**: `docker/elasticsearch/elasticsearch.yml`
- **Grafana**:
    - `docker/grafana/Dockerfile`
    - `docker/grafana/config.ini`
    - `docker/grafana/dashboards/`
    - `docker/grafana/provisioning/`
- **Kibana**: `docker/kibana/kibana.yml`
- **Logstash**:
    - `docker/logstash/Dockerfile`
    - `docker/logstash/pipeline/logstash.conf`
- **OpenTelemetry Collector**:
    - `docker/opentelemetry-collector/Dockerfile`
    - `docker/opentelemetry-collector/otel-collector-config.yml`
- **Prometheus**: `docker/prometheus/prometheus.yml`
- **Spring Boot**: `docker/springboot/Dockerfile`

## **Running Tests**

To run the tests, use the following command:

```sh
./mvnw test
```

## **Authors**

- **Your Name** - *Initial work* - [YourGitHub](https://github.com/your-github)

## **License**

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## **Acknowledgments**

- Spring Boot
- Docker
- OpenTelemetry
- Grafana
- Prometheus
- Elasticsearch
- Kibana
- Logstash
