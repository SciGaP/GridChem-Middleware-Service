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

echo "GridChem server started"

export CATALINA_HOME=/home/ccguser/apache-tomcat-6.0.35

export CATALINA_OPTS="-Xms1024m -Xmx1500m"

cd $CATALINA_HOME

bin/startup.sh

#tail -f logs/catalina.out

echo "GridChem file server started"
