/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Mar 28, 2006
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

package org.gridchem.service.beans;

import java.util.ArrayList;
import java.util.List;

import org.gridchem.service.model.enumeration.BatchSchedulerType;

/**
 * Dereferenced <code>SoftwareResource</code> object.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
@SuppressWarnings("unchecked")
public class ComputeBean extends ResourceBean implements Comparable {
    private LoadBean load;
    private long availableCpu;
    private long availableDisk;
    private BatchSchedulerType scheduler;
    private long totalMemory;
    private String system;
    private long totalNodes;
    private long totalCpu;
    private long totalDisk;
    private double peakPerformance;
    private String defaultScratchDirectory;
//    private String defaultStorageResource;
    private List<SoftwareBean> software = new ArrayList<SoftwareBean>();
    private List<String> allocations = new ArrayList<String>();
    private List<QueueBean> queues = new ArrayList<QueueBean>();
    private List<ProjectBean> projects= new ArrayList<ProjectBean>();
    


	public ComputeBean() {}
    
    //  ********************** Getters and Setters ********************** //
    
    /**
	 * @param totalDisk the totalDisk to set
	 */
	public void setTotalDisk(long totalDisk) {
		this.totalDisk = totalDisk;
	}

	/**
	 * @return the totalDisk
	 */
	public long getTotalDisk() {
		return totalDisk;
	}
	
    /**
     * @return Returns the software.
     */
    public List<SoftwareBean> getSoftware() {
        return software;
    }
    /**
     * @param software The software to set.
     */
    public void setSoftware(List<SoftwareBean> software) {
        this.software = software;
    }
    
    /**
     * @param swDTO The software to add to the set
     */
    public void addSoftware(SoftwareBean software) {
        this.software.add(software);
    }
    /**
     * @return Returns the load.
     */
    public LoadBean getLoad() {
        return load;
    }
    /**
     * @param load The load to set.
     */
    public void setLoad(LoadBean load) {
        this.load = load;
    }
    /**
     * @return the queues
     */
    public List<QueueBean> getQueues() {
        return queues;
    }

    /** 
      * @return the projects
      */
      public List<ProjectBean> getProjects() {
        return projects;
     }
    /**
     * @param queues the projects to set
     */
    public void setProjects (List<ProjectBean> projects ) {
        this.projects= projects;
    }

    /**
     * @param queues the queues to set
     */
    public void setQueues(List<QueueBean> queues) {
        this.queues = queues;
    }

    /**
     * @return the peakPerformance
     */
    public double getPeakPerformance() {
        return peakPerformance;
    }

    /**
     * @param peakPerformance the peakPerformance to set
     */
    public void setPeakPerformance(double peakPerformance) {
        this.peakPerformance = peakPerformance;
    }

    /**
     * @return the scheduler
     */
    public BatchSchedulerType getScheduler() {
        return scheduler;
    }

    /**
     * @param scheduler the scheduler to set
     */
    public void setScheduler(BatchSchedulerType scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * @return the system
     */
    public String getSystem() {
        return system;
    }

    /**
     * @param system the system to set
     */
    public void setSystem(String system) {
        this.system = system;
    }

    /**
     * @return the totalCpu
     */
    public long getTotalCpu() {
        return totalCpu;
    }

    /**
     * @param totalCpu the totalCpu to set
     */
    public void setTotalCpu(long totalCpu) {
        this.totalCpu = totalCpu;
    }

    /**
     * @return the totalNodes
     */
    public long getTotalNodes() {
        return totalNodes;
    }

    /**
     * @param totalNodes the totalNodes to set
     */
    public void setTotalNodes(long totalNodes) {
        this.totalNodes = totalNodes;
    }

    /**
	 * @param defaultScratchDirectory the defaultScratchDirectory to set
	 */
	public void setDefaultScratchDirectory(String defaultScratchDirectory) {
		this.defaultScratchDirectory = defaultScratchDirectory;
	}

	/**
	 * @return the defaultScratchDirectory
	 */
	public String getDefaultScratchDirectory() {
		return defaultScratchDirectory;
	}
//
//	/**
//	 * @param defaultStorageResource the defaultStorageResource to set
//	 */
//	public void setDefaultStorageResource(String defaultStorageResource) {
//		this.defaultStorageResource = defaultStorageResource;
//	}
//
//	/**
//	 * @return the defaultStorageResource
//	 */
//	public String getDefaultStorageResource() {
//		return defaultStorageResource;
//	}

	/**
	 * @return the defaultQueue
	 */
	public String getDefaultQueue() {
		for (QueueBean q: queues) {
			if (q.isDefaultQueue()) {
				return q.getName();
			}
		}
		return null;
	}

	/**
     * @return the availableCpu
     */
    public long getAvailableCpu() {
        return availableCpu;
    }

    /**
     * @param availableCpu the availableCpu to set
     */
    public void setAvailableCpu(long availableCpu) {
        this.availableCpu = availableCpu;
    }
    
    //  ********************** Common Methods ********************** //
    
    /**
	 * @param allocations the allocations to set
	 */
	public void setAllocations(List<String> allocations) {
		this.allocations = allocations;
	}

	/**
	 * @return the allocations
	 */
	public List<String> getAllocations() {
		return allocations;
	}

	/**
	 * @param totalMemory the totalMemory to set
	 */
	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	/**
	 * @return the totalMemory
	 */
	public long getTotalMemory() {
		return totalMemory;
	}

	/**
	 * @param availableMemory the availableMemory to set
	 */
	public void setAvailableDisk(long availableDisk) {
		this.availableDisk = availableDisk;
	}

	/**
	 * @return the availableMemory
	 */
	public long getAvailableDisk() {
		return availableDisk;
	}

	public String toString() {
        String retVal = "Hardware Resource: " + getName() + " " + ((getLoad() != null)?getLoad().toString():"");
        /*for(Iterator iter=getSoftware().iterator();iter.hasNext();) {
            SoftwareDTO swDTO = (SoftwareDTO)iter.next();
            retVal += swDTO.toString() + "\n";
        }*/
        
        return retVal;
    }

}
