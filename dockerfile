FROM eclipse-temurin:21-jre-alpine

VOLUME /tmp
WORKDIR /app

RUN apk add --no-cache htop curl

COPY build/libs/*.jar /app/app.jar

ENTRYPOINT [ "java", \
    "-Djava.security.egd=file:///dev/urandom", \
    "-Dsun.net.inetaddr.ttl=0", \
    "-Xlog:gc*=info:file=/tmp/gc.log:time,uptime,level,tags", \
    "-jar", "/app/app.jar" \
]

EXPOSE 8080