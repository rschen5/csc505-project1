#!/bin/bash

#Set up files to run sorting algorithms
#Unzip data files
gunzip -r Project1

#Create directories to store sorting outputs
mkdir outputs outputs/A outputs/B outputs/C

#Compile sorting algorithm source code
javac -d bin src/*.java

#Give sorting scripts permission to run
chmod +x src/*.sh

#Remove invalid file endings
sed -i -e 's/\r$//' src/*.sh
