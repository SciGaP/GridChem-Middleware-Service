FTD=`ps -ef | egrep  "Daemon.jar"  | grep -v grep | awk '{print $2 }'`
kill -9 $FTD
cd /home/ccguser/FileTransferDaemon
java -jar FileTransferDaemon.jar &

