#!/bin/bash
echo "Updating SeizureTracker..."
docker compose pull seizuretracker
docker compose up -d seizuretracker --force-recreate