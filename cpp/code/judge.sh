#!/bin/bash

# current workdir is /home/hwf/onlineJudge/myCode
# target workdir is /home/hwf/onlineJudge/judge

#cp judge ../judge/judge

# use "--cap-add=SYS_PTRACE" so that ptrace can be used in Docker
#1workdir 2runid 3cid 4pno 5lang 6hostname 7username 8passwd 9dbname
#judgetest sh: ./judge 1 0 1 1 192.168.106.136 root root hcoj
#docker run --rm --cap-add=SYS_PTRACE -u oj -v $1:/judge ubuntu:oj bin/bash -c "cd /judge && ./judge $2 $3 $4 $5 $6 $7 $8 $9"
docker run --rm --cap-add=SYS_PTRACE -u 1000 -v $1:/judge ubuntu_oj bin/bash -c "cd /judge && ./judge $2 $3 $4 $5 $6 $7 $8 $9"
