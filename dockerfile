FROM eclipse-temurin:21-jre-alpine

VOLUME /tmp

COPY build/libs/*.jar app.jar

RUN apk add --no-cache gcompat
RUN addgroup -S kakao && adduser -S -G kakao deploy

USER deploy

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS \
-XX:MaxRAMPercentage=75.0 \
-XX:MaxMetaspaceSize=256m \
-Djava.security.egd=file:///dev/urandom \
-Dsun.net.inetaddr.ttl=0 \
-jar /app.jar" ]