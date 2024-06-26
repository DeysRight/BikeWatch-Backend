# jar 파일 빌드
FROM openjdk:17-jdk-slim as builder
LABEL maintainer="Kyungtak Park <qkrrudxkr77@gmail.com>"

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew && ./gradlew bootJar

# jar 실행
# 빌드를 하지 않으므로 JDK가 아닌 JRE를 베이스 이미지로 세팅
FROM openjdk:17-jdk-slim as runtime
LABEL maintainer="Kyungtak Park <qkrrudxkr77@gmail.com>"

RUN addgroup --system --gid 1000 worker && adduser --system --uid 1000 --ingroup worker --disabled-password worker

COPY --from=builder build/libs/*.jar app.jar

# 파일 소유권 변경
RUN chown -f worker:worker /app.jar
USER worker:worker

ENV PROFILE ${PROFILE}
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "/app.jar"]