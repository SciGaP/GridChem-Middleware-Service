# Establish the distribution directory
GMS_HOME=/home/ccguser/GMS2.0_Synchronized
DIST=$GMS_HOME/build/classes

# Build the service
#ant jar

# Define the classpath
for i in `ls $GMS_HOME/lib/*/*.jar`; 
do 
        cp=$i:$cp; 
done; 

cp=$GMS_HOME/src/hibernate.cfg.xml:$GMS_HOME/gms.properties:$GMS_HOME/ccg-log4j.properties:$GMS_HOME/build/gms.aar:$cp

echo $cp

# Start the server
echo "Starting the Server on `date`"

#java -classpath $cp:$GMS_HOME:. -Dlog4j.debug=true -Dlog4j.configuration=ccg-log4j.properties org.gridchem.service.gms.GMSServer > $GMS_HOME/logs/gms.console &

# Record the process id
#echo $! > cap.PID


export CATALINA_HOME=/home/ccguser/production/apache-tomcat-7.0.56

export CATALINA_OPTS="-Xms1024m -Xmx1500m"

cd $CATALINA_HOME

bin/startup.sh

#tail -f logs/catalina.out

# Start Gridftp server 
#$GLOBUS_LOCATION/sbin/globus-gridftp-server

# Start FileTransferDaemon
cd /home/ccguser/FileTransferDaemon
java -jar FileTransferDaemon.jar
#echo "GridChem file server started"
echo "GridChem server with external fileservices started at `date`"
