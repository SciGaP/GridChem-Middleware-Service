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


/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
public class NetworkResource extends CCGResource {
	private String mask;
	private String networkName;
	private float bandwidth;
	private float latency;
	private float bwMaeForecast;
	private float bwMaeError;
	private float bwMseForecast;
	private float bwMseError;
	private float ltMaeForecast;
	private float ltMaeError;
	private float ltMseForecast;
	private float ltMseError;
	private CCGResource toResource;
	private CCGResource fromResource;
	
	public NetworkResource() {}
	
	/**
	 * @return Returns the bandwidth.
	 */
	public float getBandwidth() {
		return bandwidth;
	}
	/**
	 * @param bandwidth The bandwidth to set.
	 */
	public void setBandwidth(float bandwidth) {
		this.bandwidth = bandwidth;
	}
	/**
	 * @return Returns the bwMaeError.
	 */
	public float getBwMaeError() {
		return bwMaeError;
	}
	/**
	 * @param bwMaeError The bwMaeError to set.
	 */
	public void setBwMaeError(float bwMaeError) {
		this.bwMaeError = bwMaeError;
	}
	/**
	 * @return Returns the bwMaeForecast.
	 */
	public float getBwMaeForecast() {
		return bwMaeForecast;
	}
	/**
	 * @param bwMaeForecast The bwMaeForecast to set.
	 */
	public void setBwMaeForecast(float bwMaeForecast) {
		this.bwMaeForecast = bwMaeForecast;
	}
	/**
	 * @return Returns the bwMseError.
	 */
	public float getBwMseError() {
		return bwMseError;
	}
	/**
	 * @param bwMseError The bwMseError to set.
	 */
	public void setBwMseError(float bwMseError) {
		this.bwMseError = bwMseError;
	}
	/**
	 * @return Returns the bwMseForecast.
	 */
	public float getBwMseForecast() {
		return bwMseForecast;
	}
	/**
	 * @param bwMseForecast The bwMseForecast to set.
	 */
	public void setBwMseForecast(float bwMseForecast) {
		this.bwMseForecast = bwMseForecast;
	}
	/**
	 * @return Returns the fromResource.
	 */
	public CCGResource getFromResource() {
		return fromResource;
	}
	/**
	 * @param fromResource The fromResource to set.
	 */
	public void setFromResource(CCGResource fromResource) {
		this.fromResource = fromResource;
	}
	/**
	 * @return Returns the latency.
	 */
	public float getLatency() {
		return latency;
	}
	/**
	 * @param latency The latency to set.
	 */
	public void setLatency(float latency) {
		this.latency = latency;
	}
	/**
	 * @return Returns the ltMaeError.
	 */
	public float getLtMaeError() {
		return ltMaeError;
	}
	/**
	 * @param ltMaeError The ltMaeError to set.
	 */
	public void setLtMaeError(float ltMaeError) {
		this.ltMaeError = ltMaeError;
	}
	/**
	 * @return Returns the ltMaeForecast.
	 */
	public float getLtMaeForecast() {
		return ltMaeForecast;
	}
	/**
	 * @param ltMaeForecast The ltMaeForecast to set.
	 */
	public void setLtMaeForecast(float ltMaeForecast) {
		this.ltMaeForecast = ltMaeForecast;
	}
	/**
	 * @return Returns the ltMseError.
	 */
	public float getLtMseError() {
		return ltMseError;
	}
	/**
	 * @param ltMseError The ltMseError to set.
	 */
	public void setLtMseError(float ltMseError) {
		this.ltMseError = ltMseError;
	}
	/**
	 * @return Returns the ltMseForecast.
	 */
	public float getLtMseForecast() {
		return ltMseForecast;
	}
	/**
	 * @param ltMseForecast The ltMseForecast to set.
	 */
	public void setLtMseForecast(float ltMseForecast) {
		this.ltMseForecast = ltMseForecast;
	}
	/**
	 * @return Returns the mask.
	 */
	public String getMask() {
		return mask;
	}
	/**
	 * @param mask The mask to set.
	 */
	public void setMask(String mask) {
		this.mask = mask;
	}
	/**
	 * @return Returns the networkName.
	 */
	public String getNetworkName() {
		return networkName;
	}
	/**
	 * @param networkName The networkName to set.
	 */
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}
	/**
	 * @return Returns the toResource.
	 */
	public CCGResource getToResource() {
		return toResource;
	}
	/**
	 * @param toResource The toResource to set.
	 */
	public void setToResource(CCGResource toResource) {
		this.toResource = toResource;
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
				"Resource Type: '" + getType().toString() + "' " +
				"Network Mask: '" + getMask() + "' " +
				"Network Name: '" + getNetworkName() + "' " +
				"Hostname: '" + getHostname() + "' " +
				"IP Address: '" + getIpAddress() + "' " +
				"Bandwidth: '" + getBandwidth() + "' " +
				"Latency: '" + getLatency() + "' " +
				"BwMaeError: '" + getBwMaeForecast() + "' " +
				"BwMaeError: '" + getBwMaeError() + "' " +
				"BwMseForecast: '" + getBwMseForecast()+ "' " +
				"BwMseError" + getBwMseError() + "' " +
				"LtMaeForecast: '" + getLtMaeForecast() + "'" +
				"LtMaeError: '" + getLtMaeError() + "'" +
				"LtMseForecast: '" + getLtMseForecast() + "'" +
				"LtMseError: '" + getLtMseError() + "'";
	}
	
	public int compareTo(Object o) {
		if (o instanceof SoftwareInstallation)
			return this.getId().compareTo(
					((ComputeResource)o).getId() );
		return 0;
	}

	@Override
	public ResourceBean toBean() {
		return null;
	}

}
