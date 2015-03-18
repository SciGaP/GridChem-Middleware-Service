package org.gridchem.service.sync.gpir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.gridchem.service.util.Settings;
import org.jdom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * This is the utility class which queries the middleware service, parses the xml
 * and returns a string.  Methods are provided to query several different sources
 * of information as well as persist this information locally.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
public class GpirClient {
    static Document document;

   
    /** Default main method.  
     * @param args takes either 2 or 3 arguments depending on whether querying
     *             by resource or VO.  The expected usage is as follows:
     *     Query resource {jobs,load,motd,nodes,summary} <resourcename>
     *     Query {jobs,load,motd,nodes} <voname>
     *
     */
    public static void main(String [] args) {
        if(args.length == 2) { 	// by default we assume query by vo
            getQuery("vo",args[0],args[1]);
        } else if(args.length == 3) {  // otherwise they are querying by resource
            if(args[0].equals("resource")) {
                getQuery(args[0],args[1],args[2]);
            } else {
                System.out.println("Error: improper query type!!");
                printUsage();
            }
        } else {
            System.out.println("Error: not enough arguments");
            printUsage();
        }
    }
    
    /** Default constructor to be used when running main.
     */
    public GpirClient(){}

    /** Useful constructor to be used when running from code (hint, hint).
     *  @param queryType query by "vo" or "resource"
     *  @param queryName {jobs, nodes, load, motd, summary}.  There are more,
     *                   but this is all the info we're pushing at CCT right
     *                   now.
     *  @param queryArg  if queryType is "vo", then this is the name of the 
     *                   VO. Otherwise, if queryType is "resource", this is 
     *                   the resource of interest. 
     */
    public GpirClient(String queryType, String queryName, String queryArg) {
        try {
	    // GPIR service is running on gridhub
            Service service = new Service();
            Call call = (Call) service.createCall();

            call.setTargetEndpointAddress(
                    new java.net.URL(Settings.GPIR_SERVER) );

	    // Only 2 query operations are available and useful
            if(queryType.equals("vo")){
                call.setOperationName(
                    new QName("GPIRQuery", "getQueryByVo") );
            } else if(queryType.equals("resource")){
                call.setOperationName(
                    new QName("GPIRQuery", "getQueryByResource") );
            } else {
                printUsage();
            }

	    // invoke the service with the passed arguments
            Object ret = call.invoke( new Object[] { queryName, queryArg } );

	    // If you want all the messy xml output, uncomment the next line
            //System.out.println((String)ret);

	    // Otherwise, the xml will be cleaned up and printed to terminal
            File xmlFile = new File("ccg_resources.xml");
            FileOutputStream fos = new FileOutputStream(xmlFile);
            PrintStream ps = new PrintStream(fos);
            ps.println((String)ret);
            
            parseXML(xmlFile);
	  
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

    /** Parse the XML passed from the web service into human-readable format.
     * 
     * @param xmlFile
     * @return
     */
    public static void parseXML(File xmlFile) {
        XMLReader reader;
        try {
            reader = XML.makeXMLReader();
            reader.setContentHandler( new Sink() ); 
            reader.parse(new InputSource(xmlFile.getAbsolutePath()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    } // parseXLM

    /** Print correct command line usage of this class
     */
    public static void printUsage(){
            System.err.println("Usage: \n" +
                //"\t queryGPIR resource {jobs,load,motd,nodes} <resourcename>\n" +
                "\t queryGPIR {jobs,load,motd,nodes} <voname>");
            System.exit(1);
    }

    /** Same as the programmatic constructor. Basically i just wanted a static method to
     *  call.
     *  @param queryType query by "vo" or "resource"
     *  @param queryName {jobs, nodes, load, motd, summary}.  There are more,
     *                   but this is all the info we're pushing at CCT right
     *                   now.
     *  @param queryArg  if queryType is "vo", then this is the name of the 
     *                   VO. Otherwise, if queryType is "resource", this is 
     *                   the resource of interest. 
     */
    public static Properties getQuery(String queryType, String queryName, String queryArg){
        Properties props = new Properties();
        try {
	    // GPIR service is running on gridhub
            Service service = new Service();
            Call call = (Call) service.createCall();

            call.setTargetEndpointAddress(
                    new java.net.URL(Settings.GPIR_SERVER) );
            
            System.out.println("Calling " + Settings.GPIR_SERVER + " " + 
                    queryType + " " + queryName+ " " + queryArg);
            
	    // Only 2 query operations are available and useful
            if(queryType.equals("vo")){
                call.setOperationName(
                    new QName("GPIRQuery", "getQueryByVo") );
            } else if(queryType.equals("resource")){
                call.setOperationName(
                    new QName("GPIRQuery", "getQueryByResource") );
            } else {
                printUsage();
            }

	    // invoke the service with the passed arguments
            Object ret = call.invoke( new Object[] { queryName, queryArg } );

	    // If you want all the messy xml output, uncomment the next line
            System.out.println((String)ret);

	    // Otherwise, the xml will be cleaned up and printed to terminal
            File xmlFile = new File("ccg_resources.xml");
            FileOutputStream fos = new FileOutputStream(xmlFile);
            PrintStream ps = new PrintStream(fos);
            ps.println((String)ret);
            
            parseXML(xmlFile);
            fos.close();
            
        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
        return props;
    }
    
    public static String getQuery(String queryType, String queryName, 
    								String queryArg, File xmlFile) {
    		Object ret = new String("");
    		try {
    		    // GPIR service is running on gridhub
                Service service = new Service();
                Call call = (Call) service.createCall();

                call.setTargetEndpointAddress(
                        new java.net.URL(Settings.GPIR_SERVER) );

                // Only 2 query operations are available and useful
                if(queryType.equals("vo")){
                    call.setOperationName(
                        new QName("GPIRQuery", "getQueryByVo") );
                } else if(queryType.equals("resource")){
                    call.setOperationName(
                        new QName("GPIRQuery", "getQueryByResource") );
                } else {
                    printUsage();
                }

                // invoke the service with the passed arguments
                ret = call.invoke( new Object[] { queryName, queryArg } );

                // If you want all the messy xml output, uncomment the next line
//                System.out.println((String)ret);

                // Otherwise, the xml will be cleaned up and printed to terminal
                FileOutputStream fos = new FileOutputStream(xmlFile);
                PrintStream ps = new PrintStream(fos);
                ps.println((String)ret);
                fos.close();
                
    		 } catch (Exception e) {
    		 	System.err.println(e.toString());
    		 	e.printStackTrace();
    		 }
    		 return (String)ret;
    }
}
