#!/usr/bin/env bash

if [ "$1" = "native" ];
then
  ./mvnw clean -Pnative spring-boot:build-image --projects movies-api -DskipTests
else
  ./mvnw clean compile jib:dockerBuild --projects movies-api
fi
