/**
 * 
 */
package org.gridchem.service.beans;


/**
 * @author dooley
 *
 */
public class VisualizationBean extends ResourceBean {
	
	private long numNodes;
	private long numProcessors;
	private float peakPerformance;
	private long memory;
	private String scratchDisk;
	private long peakPolygons;
	private String graphicsHw;
	private SiteBean site;
	
	public VisualizationBean() {
		
	}

	/**
	 * @return the numNodes
	 */
	public long getNumNodes() {
		return numNodes;
	}

	/**
	 * @param numNodes the numNodes to set
	 */
	public void setNumNodes(long numNodes) {
		this.numNodes = numNodes;
	}

	/**
	 * @return the numProcessors
	 */
	public long getNumProcessors() {
		return numProcessors;
	}

	/**
	 * @param numProcessors the numProcessors to set
	 */
	public void setNumProcessors(long numProcessors) {
		this.numProcessors = numProcessors;
	}

	/**
	 * @return the peakPerformance
	 */
	public float getPeakPerformance() {
		return peakPerformance;
	}

	/**
	 * @param peakPerformance the peakPerformance to set
	 */
	public void setPeakPerformance(float peakPerformance) {
		this.peakPerformance = peakPerformance;
	}

	/**
	 * @return the memory
	 */
	public long getMemory() {
		return memory;
	}

	/**
	 * @param memory the memory to set
	 */
	public void setMemory(long memory) {
		this.memory = memory;
	}

	/**
	 * @return the scratchDisk
	 */
	public String getScratchDisk() {
		return scratchDisk;
	}

	/**
	 * @param scratchDisk the scratchDisk to set
	 */
	public void setScratchDisk(String scratchDisk) {
		this.scratchDisk = scratchDisk;
	}

	/**
	 * @return the peakPolygons
	 */
	public long getPeakPolygons() {
		return peakPolygons;
	}

	/**
	 * @param peakPolygons the peakPolygons to set
	 */
	public void setPeakPolygons(long peakPolygons) {
		this.peakPolygons = peakPolygons;
	}

	/**
	 * @return the graphicsHw
	 */
	public String getGraphicsHw() {
		return graphicsHw;
	}

	/**
	 * @param graphicsHw the graphicsHw to set
	 */
	public void setGraphicsHw(String graphicsHw) {
		this.graphicsHw = graphicsHw;
	}

	/**
	 * @return the site
	 */
	public SiteBean getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(SiteBean site) {
		this.site = site;
	}
	
	
}
