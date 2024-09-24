#!/bin/bash

mvn clean package 

# Controlla se Java è installato
if ! command -v java &> /dev/null
then
    echo "Java non è installato. Per favore, installa Java per eseguire questa applicazione."
    exit 1
fi

java -jar  target/demo-0.0.1-SNAPSHOT.jar

echo "executing application..."
