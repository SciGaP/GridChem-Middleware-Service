export CATALINA_HOME=/home/ccguser/apache-tomcat-6.0.35
export CATALINA_OPTS="-Xms1024m -Xmx1500m"

/home/ccguser/apache-tomcat-6.0.35/bin/shutdown.sh
sleep 5
/home/ccguser/GMS2.0_Synchronized/startup.sh &
sleep 10
