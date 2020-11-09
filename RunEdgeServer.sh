#!/bin/bash

pathis=$(pwd)

echo $pathis
mvn install:install-file -Dfile=$pathis/lib/jheatchart-0.6.jar -DgroupId=org.tc33.jheatchart -DartifactId=HeatChart -Dversion=0.6 -Dpackaging=jar


mvn package;
mvn clean install compile;
mvn exec:java;

