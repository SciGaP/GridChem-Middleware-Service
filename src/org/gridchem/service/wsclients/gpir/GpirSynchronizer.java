/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Feb 1, 2006
 * 
 * Developed by: CCT, Center for Computation and Technology, 
 * 				NCSA, University of Illinois at Urbana-Champaign
 * 				OSC, Ohio Supercomputing Center
 * 				TACC, Texas Advanced Computing Center
 * 				UKy, University of Kentucky
 * 
 * https://www.gridchem.org/
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal with the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom 
 * the Software is furnished to do so, subject to the following conditions:
 * 1. Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimers.
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimers in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the names of Chemistry and Computational Biology Group , NCSA, 
 *    University of Illinois at Urbana-Champaign, nor the names of its contributors 
 *    may be used to endorse or promote products derived from this Software without 
 *    specific prior written permission.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  
 * IN NO EVENT SHALL THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS WITH THE SOFTWARE.
*/

package org.gridchem.service.wsclients.gpir;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.LoadBean;
import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.StorageBean;
import org.gridchem.service.beans.VisualizationBean;
import org.gridchem.service.exceptions.ResourceException;
import org.gridchem.service.exceptions.SynchronizationException;
import org.gridchem.service.model.CCGResource;
import org.gridchem.service.model.ComputeResource;
import org.gridchem.service.model.Load;
import org.gridchem.service.model.VisualizationResource;
import org.gridchem.service.model.enumeration.ResourceStatusType;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.sync.ResourceDiscoveryAdaptor;
import org.gridchem.service.util.Settings;
import org.hibernate.criterion.Restrictions;


/**
 * Worker class which synchronizes the information persisted in our db with
 * the information in the information service.  In short, this class queries 
 * the info service by retrieving the xml, parsing the code, and resolving 
 * it against the data in the db.  This class should be called roughly every 
 * 10 minutes, which is the rate at which the info service refreshes.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 * @see CCGResource
 */
@SuppressWarnings({"unchecked","unused"})
public class GpirSynchronizer implements ResourceDiscoveryAdaptor {
	public static final Logger logger = Logger.getLogger(GpirSynchronizer.class);

	public GpirSynchronizer() {}
	
	public List<ResourceBean> retrieveResources() {
		
		List<ResourceBean> resourceBeans = new ArrayList<ResourceBean>();
		
		try {
			String resourceXML = retrieveXML("ccg_resources.xml");
			XmlHandler handler = null;
		    GPIRCollection collection = null;
		    List computeBeans = null;
		    List storageBeans = null;
		    List vizBeans = null;
		    List pcGridBeans = null;
		    String errorMsg = null;
		    if(resourceXML != null) {
		        handler = new SummaryXmlHandler(resourceXML);
				collection = handler.getBeans();
				
				computeBeans = collection.get(GpirProperties.COMPUTE);
				
				try {
					resourceBeans.addAll(parseResources(computeBeans));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
				
				storageBeans = collection.get(GpirProperties.STORAGE);
//                if (Settings.VERBOSE) 
//                    logger.debug("GPIR returned " + storageBeans.size() + " storage resources");
                try {
                	resourceBeans.addAll(parseResources(storageBeans));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                
				vizBeans = collection.get(GpirProperties.VIZ);
//                if (Settings.VERBOSE) 
//                    logger.debug("GPIR returned " + vizBeans.size() + " viz resources");
                try {
                	resourceBeans.addAll(parseResources(vizBeans));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                
				pcGridBeans = collection.get(GpirProperties.PC_GRID);
//                if (Settings.VERBOSE) 
//                    logger.debug("GPIR returned " + pcGridBeans.size() + " pcgrid resources");
                try {
                	resourceBeans.addAll(parseResources(pcGridBeans));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
		    } else {
		    		throw new SynchronizationException(
						"Could not parse xml returned from information service");
			}
		} catch (ResourceException e) {
			logger.error("Failed to retrieve load info from GPIR",e);
        }catch (SynchronizationException e) {
        	throw new SynchronizationException(
					"Resource Synchronization failed: " + e.getMessage(), e);
		}
        
        return resourceBeans;
	}
	/**
	 * Get the XML data from the info service
	 * @param serviceHost
	 */
	public String retrieveXML(String xmlFilename) 
	throws SynchronizationException {
		String resourceXML = "";
		try {
			resourceXML = GpirClient.getQuery("vo",
					"summary",
					Settings.VO,
					new File(xmlFilename));
			if (!resourceXML.equals(""));
				//logger.debug("Received XML from GPIR!!");
		} catch (Exception e) {
			logger.debug("No data retrieved from GPIR");
			throw new SynchronizationException(e);
		}
		return resourceXML;
	}
	
			
	/**
	 * Method determines the type of resource(s) passed to it and sends them 
	 * to be resolved by the resource-type specific synchronization methods.
	 * 
	 * @param resourceXML
	 */
	public List<ResourceBean> parseResources(List resourceList)
	throws SynchronizationException {
		
		List<ResourceBean> resourceBeans = new ArrayList<ResourceBean>();
		
		for(Object resource: resourceList) {
//			System.out.println("object is of type: " + resource.getClass().getName());
			if (resource instanceof ComputeResourceBean) {
				try {
                    resourceBeans.add(parseComputeResource((ComputeResourceBean)resource));
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
				//System.out.println(((ComputeResourceBean)resource).toString() + "\n");
			} else if (resource instanceof VizResourceBean) {
				//synchVizResource((VizResourceBean)resource);
				//System.out.println(((VizResourceBean)resource).toString());
			} else if (resource instanceof StorageResourceBean) {
				//synchStorageResource((StorageResourceBean)resource);
				//System.out.println(((StorageResourceBean)resource).toString());
			} else if (resource instanceof PcGridResourceBean) {
				try {
					resourceBeans.add(parsePcGridResource((PcGridResourceBean)resource));
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
				//System.out.println(((PcGridResourceBean)resource).toString());
			} else {
				String message = new String("Unsupported Resource Type Found: " + 
						resource.getClass().getName());
				logger.debug(message);
				throw new SynchronizationException(message);
			}
		}
		
		return resourceBeans;
	}
	
	/**
	 * Routine to resolve attributes of compute resource bean (i.e. gpir compute 
	 * resource) into a ccg compute resource object for persisting.
	 * 
	 * @param crb
	 */
	private ResourceBean parseComputeResource(ComputeResourceBean crb) {
		
		ComputeBean hpc = new ComputeBean();
		
//            System.out.println("Searching for resource matching " + crb.getName());
	
//			hpc = hpcDAO.getResourceByName(crb.getName(),false);
	
//          System.out.println("Found resource [" + hpc.getId() + "]: " + crb.getName());
		hpc.setName(crb.getName());
		
//				System.out.println("total cpu: "+(new Long(crb.getProcessors())).longValue());
		hpc.setTotalCpu((new Long(crb.getProcessors())).longValue());
		
//				System.out.println("hostname: "+crb.getHostname());
		//hpc.setHostname(crb.getHostname());
		
//				System.out.println("system: "+crb.getSystem());
		hpc.setSystem(crb.getSystem());
		
//				System.out.println("nodes: " + (new Long(crb.getNodes())).longValue());
//				System.out.println("nodes: " + crb.getNodes());
		hpc.setTotalNodes((new Long(crb.getNodes())).longValue());
		
//				System.out.println("peak performance: " + (new Float(crb.getPerformance())).floatValue());
		hpc.setPeakPerformance((new Float(crb.getPerformance())).floatValue());
		
//				System.out.println("memory: " + (new Long(crb.getMemory())).longValue());
		hpc.setTotalMemory((new Long(crb.getMemory())).longValue());
		
//				System.out.println("scratch disk: " + crb.getScratchDisk());
		hpc.setDefaultScratchDirectory(crb.getScratchDisk());
		
//				System.out.println("load disk: " + crb.getLoad());
		LoadBean load = new LoadBean();
        if (crb.getLoad() != null) {
			String[] resLoads = crb.getLoad().split(";");
			for (int i=0;i<resLoads.length;i++) {
				String[] tokens = resLoads[i].split(":");
				if(tokens[0].equals("CPU Average")) {
                    load.setCpu(new Integer(tokens[1]).intValue());
				} else if(tokens[0].equals("Disk")) {
                    load.setDisk(new Integer(tokens[1]).intValue());
				} else if(tokens[0].equals("Memory Average")) {
                    load.setMemory(new Integer(tokens[1]).intValue());
				} else if(tokens[0].equals("Queue")) {
                    load.setQueue(new Integer(tokens[1]).intValue());
				}
			}
        }
        
        load.setJobsRunning(new Long(crb.getJobsRunning()).longValue());
//                System.out.println(load.getJobsRunning());
        load.setJobsQueued(new Long(crb.getJobsQueued()).longValue());
//                System.out.println(load.getJobsQueued());
        load.setJobsOther(new Long(crb.getJobsOther()).longValue());
//                System.out.println(load.getJobsOther());
        
//                System.out.println(load.toString());
		hpc.setLoad(load);
		
		float avail = (float)(1.0 - (double)load.getCpu() / 100);
		avail = avail * (new Float(crb.getProcessors()).floatValue());
		hpc.setAvailableCpu((long)avail);
		
		avail = (float)(1.0 - (double)load.getDisk() / 100);
		avail = avail * (new Float(crb.getScratchDisk()).floatValue());
		hpc.setAvailableDisk((long)avail);
		
//		avail = (float)(1.0 - (double)load.getMemory() / 100);
//		avail = avail * (new Float(crb.getMemory()).floatValue());
//		hpc.getMemoryUsage().setBalance((long)avail);
		
//				System.out.println("status: " + (crb.getStatus().replaceAll("\"","").toUpperCase()
//						.equals(ResourceStatus.UP.toString())?ResourceStatus.UP:ResourceStatus.DOWN));
		hpc.setStatus((crb.getStatus().replaceAll("\"","").toUpperCase()
				.equals(ResourceStatusType.UP.toString())?ResourceStatusType.UP:ResourceStatusType.DOWN));
		
//				System.out.println("dept url: " +crb.getDepartmentUrl());
		hpc.setWebsite(crb.getDepartmentUrl());
		
//				System.out.println("\n");
		System.out.println("Updated " + hpc.getName() + " in the db.");
		
		return hpc;
	}
	
	/**
	 * Routine to resolve attributes of visualization resource bean (i.e. gpir viz 
	 * resource) into a ccg visualization resource object for persisting.
	 * 
	 * @param crb
	 */
	private ResourceBean parseVizResource(VizResourceBean vrb) {
		VisualizationBean viz = new VisualizationBean();
		
		viz.setName(vrb.getName());
		viz.setHostname(vrb.getHostname());
		viz.setNumProcessors((new Long(vrb.getProcessors())).longValue());
		viz.setPeakPerformance((new Float(vrb.getPerformance())).floatValue());
		viz.setMemory((new Long(vrb.getMemory())).longValue());
		viz.setPeakPolygons((new Long(vrb.getPeakPolygons())).longValue());
		viz.setGraphicsHw(vrb.getGraphicsHardware());
		
		return viz;
	}
	
	/**
	 * Routine to resolve attributes of storage resource bean (i.e. gpir storage 
	 * resource) into a ccg storage resource object for persisting.
	 * 
	 * @param crb
	 */
	private ResourceBean parseStorageResource(StorageResourceBean srb) {
		StorageBean storage = new StorageBean();
		
		storage.setName(srb.getName());
		
		return storage;
	}
	
	/**
	 * Routine to resolve attributes of PcGrid resource bean (i.e. gpir PcGrid 
	 * resource) into a ccg compute resource object for persisting.
	 * 
	 * @param crb
	 */
	private ResourceBean parsePcGridResource(PcGridResourceBean crb) {
		ComputeBean pcGrid = new ComputeBean();
       
        
//                System.out.println("Found resource [" + pcGrid.getId() + "]: " + crb.getName());
        pcGrid.setName(crb.getName());
        
//                System.out.println("total cpu: "+(new Long(crb.getTotalCpu())).longValue());
        pcGrid.setTotalCpu((new Long(crb.getTotalCpu())).longValue());
        
//                System.out.println("hostname: "+crb.getHostname());
        pcGrid.setHostname(crb.getHostname());
        
//                System.out.println("system: "+crb.getSystem());
        pcGrid.setSystem(crb.getSystem());
        
//                System.out.println("nodes: " + (new Long(crb.getTotalPc())).longValue());
        //System.out.println("nodes: " + crb.getNodes());
        pcGrid.setTotalNodes((new Long(crb.getTotalPc())).longValue());
        
        //System.out.println("load disk: " + crb.getLoad());
        LoadBean load = new LoadBean();
        double active = new Double(crb.getActiveCpu()).doubleValue();
        double total = new Double(crb.getTotalCpu()).doubleValue();
        load.setCpu(new Double(Math.ceil(active/total*100)).intValue());
        load.setDisk(0);
        load.setMemory(100);
        load.setQueue(0);
        load.setJobsRunning(new Double(active).longValue());
        load.setJobsQueued(new Double(total-active).longValue());
        load.setJobsOther(0);
        
//                System.out.println(load.toString());
        pcGrid.setLoad(load);
        
        float avail = (float)(1.0 - (double)load.getCpu() / 100);
        avail = avail * (new Float(crb.getTotalCpu()).floatValue());
        pcGrid.setAvailableCpu((long)avail);
        
//        avail = (float)(1.0 - (double)load.getMemory() / 100);
//        avail = avail * (new Float(crb.getTotalMemory()).floatValue());
//        pcGrid.getMemoryUsage().setBalance((long)avail);
        
        //System.out.println("status: " + (crb.getStatus().replaceAll("\"","").toUpperCase()
        //      .equals(ResourceStatus.UP.toString())?ResourceStatus.UP:ResourceStatus.DOWN));
        pcGrid.setStatus((crb.getStatus().replaceAll("\"","").toUpperCase()
                .equals(ResourceStatusType.UP.toString())?ResourceStatusType.UP:ResourceStatusType.DOWN));
        
//                System.out.println("dept url: " +crb.getDepartmentUrl());
        pcGrid.setWebsite(crb.getDepartmentUrl());
                
        return pcGrid;
	}
	
}
