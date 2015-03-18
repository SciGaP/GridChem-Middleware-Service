echo "stopping server..."

kill `cat cap.PID`

echo "stopping file server"

export CATALINA_HOME=/home/globus/nik_GMS/apache-tomcat-5.5.28

cd $CATALINA_HOME

bin/shutdown.sh

# Establish the distribution directory
GMS_HOME=/home/globus/nik_GMS/GMS2.0
DIST=$GMS_HOME/build/classes


echo "GridChem server started"

export CATALINA_HOME=/home/globus/nik_GMS/apache-tomcat-5.5.28

cd $CATALINA_HOME

bin/startup.sh

tail -f logs/catalina.out

export "GridChem file server started"
