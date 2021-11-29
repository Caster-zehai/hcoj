#!/bin/bash

# current workdir is /home/sean/code
# target workdir is /home/sean/code/workdir

#cp judge.cpp ./workdir/judge.cpp

# use "--cap-add=SYS_PTRACE" so that ptrace can be used in Docker
#1workdir 2runid 3cid 4pno 5lang 6hostname 7username 8passwd 9dbname
docker run --rm --cap-add=SYS_PTRACE -u root -v $1:/judge ubuntu_oj bin/bash -c "cd /judge && g++ judge.cpp -o judge -lmysqlcppconn && ./judge $2 $3 $4 $5 $6 $7 $8 $9"
