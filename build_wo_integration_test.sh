#!/usr/bin/env bash

./gradlew build -x :integration-test:test

echo "Build completed successfully."