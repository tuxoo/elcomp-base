# Backend application ElcompBase storage of electronic components 

###
- Java 17
- SPRING WEB
- SPRING JDBC
- SPRING SECURITY
- CAFFEINE

For application need EnvFile by Borys Pierov plugin and .env file which contains:
```dotenv
POSTGRES_VERSION=14
POSTGRES_PORT=[your postgres port here]
POSTGRES_SCHEMA=[your postgres schema here]
POSTGRES_DB=[your postgres db here]
POSTGRES_USER=[your postgres user here]
POSTGRES_PASSWORD=[your postgres password here]

LIQUIBASE_VERSION=4.11

GRAFANA_VERSION=9.0.2
GRAFANA_USER=[your grafana user here]
GRAFANA_PASSWORD=[your grafana password here]
GRAFANA_PORT=[your grafana port here]

PROMETHEUS_VERSION=v2.36.2
PROMETHEUS_PORT=[your prometheus port here]

PASSWORD_SALT=[your salt here]
JWT_SIGNING_KEY=[your signing key here]
```

For running application need to build:
```dotenv
gradle build -x test
```
For running application in Docker need to:
```dotenv
docker compose up
```

Swagger documentation http://localhost:9000/swagger-ui/index.html
