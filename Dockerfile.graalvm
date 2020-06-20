FROM oracle/graalvm-ce:20.1.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/demo
WORKDIR /home/app/demo

RUN native-image --no-server -cp build/libs/demo-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/demo/demo /app/demo
ENTRYPOINT ["/app/demo"]
