#!/bin/sh
if [ "$1" = "build" ];then
    mkdir /home/changsheng/project/project79023/project
    cp -a /home/changsheng/project/project79023/server/. /home/changsheng/project/project79023/project/
    cd /home/changsheng/project/project79023/project
    rm -rf /home/changsheng/project/project79023/server
    echo "执行成功"
fi
