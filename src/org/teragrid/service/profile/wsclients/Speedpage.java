/* 
 * Created on March 27, 2009
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.teragrid.service.profile.wsclients;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.teragrid.service.tgcdb.dto.BandwidthMeasurement;
import org.teragrid.service.tgcdb.dto.ComputeDTO;
import org.teragrid.service.util.TGUtil;

/**
 * Simple utility for finding today's measured bandwith between two resources through 
 * scraping of the PSC Speedpage.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 * 
 * TODO: turn into restful service:
 * web-apps/resource-monitor-v1/bandwith : returns all to all measurements
 * web-apps/resource-monitor-v1/bandwith/resource_id/$ResourceID : returns all measurements from $ResourceId to everywhere else
 * web-apps/resource-monitor-v1/bandwith/tgcdb_id/$tgcdb_id : returns all measurements from $tgcdb to everywhere else
 * 
 */
public class Speedpage {
	
	private static Logger log = Logger.getLogger(CtssResourceClient.class);
 
    private Map<String,List<BandwidthMeasurement>> measurementCache = new HashMap<String,List<BandwidthMeasurement>>();
    
    private String endpoint;
    protected Set<ComputeDTO> systems;
    
    public Speedpage(String endpoint, Set<ComputeDTO> systems) {
    	this.endpoint = endpoint;
    	this.systems = systems;
    }
    
    public BandwidthMeasurement getMeasuredBandwidth(ComputeDTO source, ComputeDTO dest) throws Exception {
//    	String src = getSpeedpageResourceName(source.getLoginHostname());
//    	String dst = getSpeedpageResourceName(dest.getLoginHostname());
    	
        if (measurementCache.containsKey(source.getResourceId())
        		&& !measurementCache.isEmpty()) {
        	if (!measurementCache.get(source.getResourceId()).contains(dest.getResourceId())) {
        		cacheMeasurements(source);
        	}
        } else { 
        	cacheMeasurements(source);
        }
        List<BandwidthMeasurement> measurements = measurementCache.get(source.getResourceId());
        for (BandwidthMeasurement bm: measurements) {
        	if (bm.getToResourceID().equals(dest.getResourceId())) {
        		return bm;
        	}
        }
        return null;
    }
    
    public List<BandwidthMeasurement> getMeasuredBandwidthsFromSite(ComputeDTO source) throws Exception {
    	String src = getSpeedpageResourceName(source.getLoginHostname());
    	
        if (!measurementCache.containsKey(source.getResourceId()) 
        		|| measurementCache.isEmpty()) {
        	cacheMeasurements(source);
        }
        
        return measurementCache.get(src);
        
    }
    
    public Map<String,List<BandwidthMeasurement>> getAllMeasuredBandwidths() throws Exception {
    	
    	if (measurementCache.isEmpty()){
    		cacheAllMeasurements();
    	}
    	
    	return measurementCache;
    }
    
    public static String getSpeedpageResourceName(String name) throws IOException{
    	if (name.equalsIgnoreCase("bigred.iu.teragrid.org")) return "IU BigRed";
    	if (name.equalsIgnoreCase("queenbee.loni-lsu.teragrid.org")) return "LONI Queenbee";
    	if (name.equalsIgnoreCase("frost.ncar.teragrid.org")) return "NCAR frost";
    	if (name.equalsIgnoreCase("abe.ncsa.teragrid.org")) return "NCSA Abe";
    	if (name.equalsIgnoreCase("cobalt.ncsa.teragrid.org")) return "NCSA Cobalt Altix";
//    	if (name.equalsIgnoreCase("lincoln.ncsa.teragrid.org")) return "NCSA Lincoln";
    	if (name.equalsIgnoreCase("kraken.nics.teragrid.org")) return "NICS Kraken Cray XT5";
    	if (name.equalsIgnoreCase("tungsten.ncsa.teragrid.org")) return "NCSA Tungsten";
    	if (name.equalsIgnoreCase("nstg.ornl.teragrid.org")) return "ORNL Neutron Science TeraGrid Gateway";
    	if (name.equalsIgnoreCase("bigben.psc.teragrid.org")) return "PSC Bigben";
    	if (name.equalsIgnoreCase("pople.psc.teragrid.org")) return "PSC Pople";
    	if (name.equalsIgnoreCase("rachel.psc.teragrid.org")) return "PSC Rachel";
    	if (name.equalsIgnoreCase("steele.purdue.teragrid.org")) return "Purdue Steele";
    	if (name.equalsIgnoreCase("condor.purdue.teragrid.org")) return "Purdue Condor";
    	if (name.equalsIgnoreCase("dtf.sdsc.teragrid.org")) return "SDSC DTF";
    	if (name.equalsIgnoreCase("frost.ncar.teragrid.org")) return "frost.ncar.teragrid.org";
    	if (name.equalsIgnoreCase("dtf.ncsa.teragrid.org")) return "dtf.ncsa.teragrid.org";
    	if (name.equalsIgnoreCase("lonestar.tacc.teragrid.org")) return "TACC Lonestar";
    	if (name.equalsIgnoreCase("maverick.tacc.teragrid.org")) return "TACC Maverick";
    	if (name.equalsIgnoreCase("spur.tacc.teragrid.org")) return "TACC Spur";
    	if (name.equalsIgnoreCase("ranger.tacc.teragrid.org")) return "TACC Ranger";
    	if (name.equalsIgnoreCase("ranch.tacc.teragrid.org")) return "TACC Ranch";
    	if (name.equalsIgnoreCase("dtf.uc.teragrid.org")) return "UC/ANL DTF";
    	if (name.equalsIgnoreCase("dtf.sdsc.teragrid.org")) return "SDSC DTF";
    	
    	if (name.equalsIgnoreCase("Big Red")) return "IU BigRed";
    	if (name.equalsIgnoreCase("Queenbee")) return "LONI Queenbee";
    	if (name.equalsIgnoreCase("Frost")) return "NCAR frost";
    	if (name.equalsIgnoreCase("Abe")) return "NCSA Abe";
//    	if (name.equalsIgnoreCase("Lincoln")) return "NCSA Lincoln";
    	if (name.equalsIgnoreCase("Cobalt")) return "NCSA Cobalt Altix";
    	if (name.equalsIgnoreCase("Kraken")) return "NICS Kraken Cray XT5";
    	if (name.equalsIgnoreCase("Tungsten")) return "NCSA Tungsten";
    	if (name.equalsIgnoreCase("NSTG")) return "ORNL Neutron Science TeraGrid Gateway";
    	if (name.equalsIgnoreCase("BigBen")) return "PSC Bigben";
    	if (name.equalsIgnoreCase("Pople")) return "PSC Pople";
    	if (name.equalsIgnoreCase("Rachel")) return "PSC Rachel";
    	if (name.equalsIgnoreCase("Steele")) return "Purdue Steele";
    	if (name.equalsIgnoreCase("SDSC TeraGrid Cluster")) return "SDSC DTF";
    	if (name.equalsIgnoreCase("Frost")) return "frost.ncar.teragrid.org";
    	if (name.equalsIgnoreCase("BigBen")) return "dtf.ncsa.teragrid.org";
    	if (name.equalsIgnoreCase("Lonestar")) return "TACC Lonestar";
    	if (name.equalsIgnoreCase("Maverick")) return "TACC Maverick";
    	if (name.equalsIgnoreCase("Ranger")) return "TACC Ranger";
    	if (name.equalsIgnoreCase("Ranch")) return "TACC Ranch";
    	if (name.equalsIgnoreCase("UC/ANL TeraGrid Cluster")) return "UC/ANL DTF";
           
    	throw new IOException("Failed to map resource");
    }
    
    /**
     * Query speedpage for all bandwidth measurement from the source resource. 
     * 
     * @param source
     * @param dest
     * @throws Exception
     */
    private void cacheMeasurements(ComputeDTO source) throws Exception {
    	
    	String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        
        String arguments = "?list=" + source.getName().replaceAll(" ", "+") + "&" +
            "begin=" + date;
        
        URL url = new URL(endpoint + arguments);
        
    	SpeedpageParser parser = new SpeedpageParser(getWebpage(url));
    	
    	measurementCache.put(source.getResourceId(), parser.getBandwidthMeasurementsAtResource(source.getResourceId()));
    	
//    	log.debug("Key entered was " + measurementCache.keySet().iterator().next());
    }
    
    /**
     * Query speedpage for all to all bandwidth measurements.
     * 
     * @throws Exception
     */
    private void cacheAllMeasurements() throws Exception {
    	String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        
        String arguments = "?begin="+date;
        	
    	URL url = new URL(endpoint + arguments);
        
    	SpeedpageParser parser = new SpeedpageParser(getWebpage(url));
    	
    	measurementCache.clear();
    	
    	measurementCache.putAll(parser.getAllBandwidthMeasurements());
    	
    }
    
    /**
     * Returns the content at the given url as a string. 
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public static InputStream getWebpage(URL url) throws IOException {
    	
    	// get page
        URLConnection conn = url.openConnection();
        return new DataInputStream ( conn.getInputStream (  )  ) ;
    }
    
    public static void main(String[] args) {
        try {
        	
        	TGResourceClient tg = new TGResourceClient("http://info.teragrid.org/web-apps/csv/tg-resources-v1/");
        	
        	Speedpage speedpage = new Speedpage("http://quipu.psc.teragrid.org/speedpage/www/speedpage.php",tg.getResources());
        	
//        	for(String key: speedpage.getAllMeasuredBandwidths().keySet()) {
//        		for(BandwidthMeasurement bm: speedpage.getAllMeasuredBandwidths().get(key)) {
//        			System.out.println(bm.toCsv());
//        		}
//        	}
        	
        	ComputeDTO source = new ComputeDTO();
        	source.setResourceId("bigred.iu.teragrid.org");
        	source.setName("IU BigRed");
        	ComputeDTO dest = new ComputeDTO();
        	dest.setResourceId("ranch.tacc.teragrid.org");
        	dest.setName("TACC Ranch");
	        	
			System.out.println("Bandwidth is: " + speedpage.getMeasuredBandwidth(source,dest).getMegabytespersecond());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
    }
	
	class SpeedpageParser {
	    
		private Logger log = Logger.getLogger(SpeedpageParser.class);
		
	    private Map<String,List<BandwidthMeasurement>> measurements = new HashMap<String,List<BandwidthMeasurement>>();
	    private Document doc;
	    
	    @SuppressWarnings("unchecked")
		public SpeedpageParser(InputStream in) throws Exception {
	        doc = new SAXBuilder(true).build(in);
	//        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
	//        FileWriter writer = new FileWriter("/Users/dooley/Desktop/speedpage.html");
	//        out.output(doc, writer);
	//        writer.close();
	//        
	        Element root = doc.getRootElement();
	        Element body = root.getChild("body",root.getNamespace());
	        Element table = body.getChild("table",root.getNamespace());
	        Element siteTable = null;
	        
	        try {
	        	siteTable = table.getChild("tr",root.getNamespace())
				.getChild("td",root.getNamespace())
				.getChild("table",root.getNamespace());
	        } catch (Exception e) {}
	        
	        if (siteTable != null) {
	        	for (Element t: (List<Element>)table.getChild("tr",root.getNamespace())
	        			.getChild("td",root.getNamespace())
	        			.getChildren("table",root.getNamespace())) {
	        		parseFullTable(t);
	        	}
	        } else {
	        	parseListingTable(table);
	        	
	        }
	    }
	        
	    @SuppressWarnings("unchecked")
		private void parseListingTable(Element e) throws ParseException {
	        List<Element> rows = e.getChildren();
	        List<BandwidthMeasurement> bms = new ArrayList<BandwidthMeasurement>();
	        
	        for(int i=1;i<rows.size();i++) {
	        	Element row = rows.get(i);
	        	List<Element> columns = row.getChildren();
	        	String sDate = columns.get(0).getTextTrim();
	        	String source = columns.get(1).getTextTrim();
	        	String dest = columns.get(2).getTextTrim();
	        	String tput = columns.get(3).getTextTrim();
//	        	log.debug("Table entries: " + sDate + ", " + source + ", " + dest + ", " + tput);
	        	
	        	Date date = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy").parse(sDate);
	        	ComputeDTO from = null;
	        	ComputeDTO to = null;
	        	for (ComputeDTO system : systems) {
	        		if (system.getName().equals(source)) {
	        			from = system;
	        		} 
	        		
	        		if (system.getName().equals(dest)) {
	        			to = system;
	        		}
	        	}
	        	String destPath = "";
	        	String srcPath = "";
	        	if (dest.contains("->")) {
					to = from;
					srcPath = dest.split("->")[0].trim();
					destPath = dest.split("->")[1].trim();
				} else {
					srcPath = "file";
					destPath = "file";
				}
	        	SimpleDateFormat utc = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	        	BandwidthMeasurement bm = new BandwidthMeasurement(from.getResourceId(),
	        			from.getName(), srcPath, to.getResourceId(), to.getName(), destPath,
	        			utc.format(date),formatThruput(tput));
//	        	log.debug("Processed measurement " + bm.toCsv());
	        	// only keep the most recent measurement
	        	if (bms.contains(bm)) {
	        		bms.remove(bm);
	        	}
	        	
	        	bms.add(bm);
	        	
	        }
	        
	        measurements.put(bms.get(0).getFromResourceID(), bms);
	        
	        log.debug("Parsed " + bms.size() + " measurements");
	    }
	    
	    @SuppressWarnings("unchecked")
		private void parseFullTable(Element eTable) throws ParseException {
	        List<Element> rows = eTable.getChildren();
	        List<BandwidthMeasurement> bms = new ArrayList<BandwidthMeasurement>();
	        
	        // get from hostname
	        String src = rows.get(0).getChild("td",eTable.getNamespace()).getChildText("a",eTable.getNamespace());
	        ComputeDTO from = null;
	    	for (ComputeDTO system : systems) {
	    		if (system.getName().equals(src)) {
	    			from = system;
	    		}
	    	}
	    	
	    	// get measurement date
	    	List<Element> columns = rows.get(1).getChildren();
	    	String sDate = columns.get(1).getTextTrim();
	    	Date date = new SimpleDateFormat("MM/dd/yyyy").parse(sDate);
	    	
//	    	log.debug("Full table entries for " + from.getResourceId() + " on " + date);
	    	
	        for(int i=3;i<rows.size();i++) {
	        	columns = rows.get(i).getChildren();
	        	String dest = columns.get(0).getTextTrim();
	        	String tput = columns.get(3).getChildTextTrim("a", eTable.getNamespace());
	        	
	        	ComputeDTO to = null;
	        	String destPath = "";
	        	String srcPath = "";
	        	
	        	if (dest.contains("->")) {
					to = from;
					srcPath = dest.split("->")[0].trim();
					destPath = dest.split("->")[1].trim();
				} else {
					for (ComputeDTO system : systems) {
		        		if (system.getName().equals(dest)) {
		        			to = system;
		        		}
		        	}
					srcPath = "file";
					destPath = "file";
				}
//	        	log.debug("Table entries: " + from.getResourceId() + ", " + to.getResourceId() + ", " + date + ", " + tput);
	        	
	        	BandwidthMeasurement bm = new BandwidthMeasurement(from.getResourceId(),
	        			from.getName(), srcPath, to.getResourceId(), to.getName(), destPath,
	        			TGUtil.formatUTC(date),formatThruput(tput));
	        	
	        	// only keep the most recent measurement
	        	if (bms.contains(bm)) {
	        		bms.remove(bm);
	        	}
	        	
	        	bms.add(bm);
	        	
	        }
	        
	        measurements.put(from.getResourceId(), bms);
	        
//	        log.debug("Parsed " + bms.size() + " measurements");
	    }
	    
	    private Double formatThruput(String tput) {
	    	if (tput == null || tput.equals("") || tput.equals("N/A")) {
	    		return Double.valueOf(0.0);
	    	} else {
	    		return Double.valueOf(tput.replaceAll(",", ""));
	    	}
	    }
	    
	    public Map<String,List<BandwidthMeasurement>> getAllBandwidthMeasurements() {
	    	return measurements;
	    }
	    
	    public List<BandwidthMeasurement> getBandwidthMeasurementsAtResource(String source) {
	    	return measurements.get(source);
	    }
	    
	    public BandwidthMeasurement getBandwidthMeasurementBetweenResources(String source, String dest) {
	    	return measurements.get(source).get(measurements.get(source).indexOf(dest));
	    }
	    
	}
}
