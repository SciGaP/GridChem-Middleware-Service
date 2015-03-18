/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Jan 19, 2006
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

package org.gridchem.service.model;

import org.gridchem.service.beans.ResourceBean;
import org.gridchem.service.beans.VisualizationBean;


/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
public class VisualizationResource extends CCGResource {
	private long numNodes;
	private long numProcessors;
	private float peakPerformance;
	private long memory;
	private String scratchDisk;
	private long peakPolygons;
	private String graphicsHw;
	
	/**
	 * Default no-arg constructors
	 */
	public VisualizationResource() {}
	
	/**
	 * @param numNodes
	 * @param numProcessors
	 * @param peakPerformance
	 * @param memory
	 * @param scratchDisk
	 * @param peakPolygons
	 * @param graphicsHw
	 */
	public VisualizationResource(long numNodes, long numProcessors,
			float peakPerformance, long memory, String scratchDisk,
			long peakPolygons, String graphicsHw) {
		this.numNodes = numNodes;
		this.numProcessors = numProcessors;
		this.peakPerformance = peakPerformance;
		this.memory = memory;
		this.scratchDisk = scratchDisk;
		this.peakPolygons = peakPolygons;
		this.graphicsHw = graphicsHw;
	}
	
	/**
	 * @return Returns the graphicsHw.
	 */
	public String getGraphicsHw() {
		return graphicsHw;
	}
	/**
	 * @param graphicsHw The graphicsHw to set.
	 */
	public void setGraphicsHw(String graphicsHw) {
		this.graphicsHw = graphicsHw;
	}
	/**
	 * @return Returns the memory.
	 */
	public long getMemory() {
		return memory;
	}
	/**
	 * @param memory The memory to set.
	 */
	public void setMemory(long memory) {
		this.memory = memory;
	}
	/**
	 * @return Returns the numNodes.
	 */
	public long getNumNodes() {
		return numNodes;
	}
	/**
	 * @param numNodes The numNodes to set.
	 */
	public void setNumNodes(long numNodes) {
		this.numNodes = numNodes;
	}
	/**
	 * @return Returns the numProcessors.
	 */
	public long getNumProcessors() {
		return numProcessors;
	}
	/**
	 * @param numProcessors The numProcessors to set.
	 */
	public void setNumProcessors(long numProcessors) {
		this.numProcessors = numProcessors;
	}
	/**
	 * @return Returns the peakPerformance.
	 */
	public float getPeakPerformance() {
		return peakPerformance;
	}
	/**
	 * @param peakPerformance The peakPerformance to set.
	 */
	public void setPeakPerformance(float peakPerformance) {
		this.peakPerformance = peakPerformance;
	}
	/**
	 * @return Returns the peakPolygons.
	 */
	public long getPeakPolygons() {
		return peakPolygons;
	}
	/**
	 * @param peakPolygons The peakPolygons to set.
	 */
	public void setPeakPolygons(long peakPolygons) {
		this.peakPolygons = peakPolygons;
	}
	/**
	 * @return Returns the scratchDisk.
	 */
	public String getScratchDisk() {
		return scratchDisk;
	}
	/**
	 * @param scratchDisk The scratchDisk to set.
	 */
	public void setScratchDisk(String scratchDisk) {
		this.scratchDisk = scratchDisk;
	}
	// ********************** Common Methods ********************** //
	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ComputeResource)) return false;
		final ComputeResource computeResource = (ComputeResource) o;
		if (!id.equals(computeResource.id)) return false;
		if (!name.equals(computeResource.name)) return false;
		if (!comment.equals(computeResource.comment)) return false;
		if (!getType().equals(computeResource.comment)) return false;
		return true;
	}
	
	public int hashCode() {
		return (new String(this.getId()+
				this.getName())).hashCode();
	}
	
	public String toString() {
		return  "Compute Resource: (\"" + getId().toString() + "\") " +
				"Name: '" + getName() + "' " +
				"Comment: '" + getComment() + "' " +
				"Resource Type: '" + getType() + "' " +
				"Total nodes: '" + getNumNodes() + "' " +
				"Total CPU: '" + getNumProcessors() + "' " +
				"Hostname: '" + getHostname() + "' " +
				"IP Address: '" + getIpAddress() + "' " +
				"Memory: '" + getMemory() + "' " +
				"Scratch Disk: '" + getScratchDisk() + "' " +
				"Peak Polygons: '" + getPeakPolygons() + "' " +
				"Graphics hardware: '" + getGraphicsHw() + "' " +
				"Last Down Time: '" + getLastDownTime().toString() + "'" +
				"Last Update: '" + getLastUpdated().toString() + "'" +
				"Created: '" + getCreated().toString() + "'" +
				"Status: '" + getStatus().toString() + "' " ;
	}
	
	public int compareTo(Object o) {
		if (o instanceof SoftwareInstallation)
			return this.getId().compareTo(
					((ComputeResource)o).getId() );
		return 0;
	}

	@Override
	public ResourceBean toBean() {
		VisualizationBean bean = new VisualizationBean();
		bean.setNumNodes(numNodes);
		bean.setNumProcessors(numProcessors);
		bean.setPeakPerformance(peakPerformance);
		bean.setMemory(memory);
		bean.setPeakPolygons(peakPolygons);
		bean.setGraphicsHw(graphicsHw);
		bean.setSite(site.toBean());
		
		return bean;
		
	}

}
