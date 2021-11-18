#!/bin/bash

# compile polling.cpp : g++ -Wall --static -std=c++11 polling.cpp -o polling -lmysqlcppconn 

# compile polling.cpp : g++ polling.cpp -o polling -lmysqlcppconn

hostname="192.168.106.136"
username="root"
passwd="root"
dbname="hcoj"
judge="./judge.sh"
workdir="./workdir"
#./polling ${hostname} ${username} ${passwd} ${dbname} ${judge} ${workdir}
./polling 192.168.106.136 root root hcoj ./judge.sh ./workdir
