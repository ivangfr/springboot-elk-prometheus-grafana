#!/usr/bin/env bash

#if [ "$1" = "native" ];
#then
#  ./mvnw clean spring-boot:build-image --projects movies-api
#else
  ./mvnw clean compile jib:dockerBuild --projects movies-api
#fi
