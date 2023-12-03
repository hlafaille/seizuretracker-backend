#!/bin/bash

docker run --name mariadb -p 3306:3306 -e MARIADB_ROOT_PASSWORD=my-secret-pw -e MARIADB_DATABASE=seizuretracker -d mariadb:10.11