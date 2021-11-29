#!/bin/bash
# compile polling.cpp : g++ -Wall --static -std=c++11 polling.cpp -o polling -lmysqlcppconn 
# compile polling.cpp : g++ polling.cpp -o polling -lmysqlcppconn
#使用shell脚本时，从windows放入的.sh文件需要先转换成unix体系的.sh脚本去除\r，使用命令dos2unix filename.sh
#./polling ${hostname} ${username} ${passwd} ${dbname} ${judge} ${workdir}
./polling 172.22.4.210 root root hcoj ./judge.sh /judge/workdir
