FROM warmhouse-gradle-builder AS build

WORKDIR /app


COPY build.gradle settings.gradle ./
RUN --mount=type=cache,target=/root/.gradle/caches \
    ./gradlew build || true

COPY src ./src
RUN --mount=type=cache,target=/root/.gradle/caches \
    ./gradlew bootJar -x test

FROM openjdk:25-ea-jdk-slim

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime  \
    && echo $TZ > /etc/timezone \
    && apt-get update \
    && apt-get install -y curl jq

WORKDIR /app

COPY --from=build /app/build/libs/*.jar temperature-api.jar

ENTRYPOINT exec java -jar /app/temperature-api.jar