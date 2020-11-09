#!/bin/bash


comname=$1

git add .
git commit -m "$comname"

git push scanlab main
git push gitlab main
git push github main

