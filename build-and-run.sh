#!/bin/bash

echo "-------------------------------------------------------"
echo "creating docker network"
docker network create --subnet=172.18.0.0/16 easynet

echo "-------------------------------------------------------"
echo "running mongo docker"
docker run --name mongodb -p 27017:27017 --net easynet --ip 172.18.0.10 -d mongo

echo "-------------------------------------------------------"
echo "running gradle build"
docker run --rm --net="host" -v "$PWD":/home/gradle/project -w /home/gradle/project gradle gradle build

echo "-------------------------------------------------------"
echo "creating docker image"
docker build -t easyshopping .

echo "-------------------------------------------------------"
echo "running app docker"
docker run -p 8080:8080 --net easynet --name easyshopping -d easyshopping

echo "-------------------------------------------------------"
echo "app running"