#!/bin/bash
cd ado-service
mvn clean package
cd ../athlete-service
mvn clean package
cd ../user-service
mvn clean package
cd ..
docker-compose build
docker-compose up -d