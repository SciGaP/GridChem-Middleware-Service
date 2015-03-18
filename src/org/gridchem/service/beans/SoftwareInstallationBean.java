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

import java.util.Date;


/**
 * This is the Software data transfer object.  It differs from the 
 * Software object in that it contains composite information from the software 
 * and hardware references in the original SoftwareInstallations objects .  A Set  
 * of these objects will be added to a VO object and serialized using xstream to 
 * the client.
 * 
 * The benefit of this approach is that the client no longer has to parse through
 * abstract data formats to figure out the multitude of application/hpc resource 
 * combinations.  They are all included in these objects. 
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 * 
 * @see Software
 * @see ComputeResource
 * @see SoftwareInstallation
 */
public class SoftwareInstallationBean extends SoftwareBean {
	private String softwareName;
    private String systemName;
    private Date compileDate;
    private String compilerName;
    private String compilerVersion;
    private String homeDir;
    private String startupEnvironment;
    private String softEnv;
    private String executablePath;
    private String scriptPath;
    private String module;
    private String arguments;
    private String usage;
    private Integer totalLicenses;
    
    public SoftwareInstallationBean() {}
    
    //  ********************** Getters and Setters ********************** //

	/**
	 * @return the compilerName
	 */
	public String getCompilerName() {
		return compilerName;
	}

	/**
	 * @param compileDate the compileDate to set
	 */
	public void setCompileDate(Date compileDate) {
		this.compileDate = compileDate;
	}

	/**
	 * @return the compileDate
	 */
	public Date getCompileDate() {
		return compileDate;
	}

	/**
	 * @param compilerName the compilerName to set
	 */
	public void setCompilerName(String compilerName) {
		this.compilerName = compilerName;
	}

	/**
	 * @param compilerVersion the compilerVersion to set
	 */
	public void setCompilerVersion(String compilerVersion) {
		this.compilerVersion = compilerVersion;
	}

	/**
	 * @return the compilerVersion
	 */
	public String getCompilerVersion() {
		return compilerVersion;
	}

	/**
	 * @return the homeDir
	 */
	public String getHomeDir() {
		return homeDir;
	}

	/**
	 * @param homeDir the homeDir to set
	 */
	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	/**
	 * @return the startupEnvironment
	 */
	public String getStartupEnvironment() {
		return startupEnvironment;
	}

	/**
	 * @param startupEnvironment the startupEnvironment to set
	 */
	public void setStartupEnvironment(String startupEnvironment) {
		this.startupEnvironment = startupEnvironment;
	}

	/**
	 * @return the softEnv
	 */
	public String getSoftEnv() {
		return softEnv;
	}

	/**
	 * @param softEnv the softEnv to set
	 */
	public void setSoftEnv(String softEnv) {
		this.softEnv = softEnv;
	}

	/**
	 * @return the executablePath
	 */
	public String getExecutablePath() {
		return executablePath;
	}

	/**
	 * @param executablePath the executablePath to set
	 */
	public void setExecutablePath(String executablePath) {
		this.executablePath = executablePath;
	}

	/**
	 * @return the scriptPath
	 */
	public String getScriptPath() {
		return scriptPath;
	}

	/**
	 * @param scriptPath the scriptPath to set
	 */
	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the arguments
	 */
	public String getArguments() {
		return arguments;
	}

	/**
	 * @param arguments the arguments to set
	 */
	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	/**
	 * @return the usage
	 */
	public String getUsage() {
		return usage;
	}

	/**
	 * @param usage the usage to set
	 */
	public void setUsage(String usage) {
		this.usage = usage;
	}

	/**
	 * @return the totalLicenses
	 */
	public Integer getTotalLicenses() {
		return totalLicenses;
	}

	/**
	 * @param totalLicenses the totalLicenses to set
	 */
	public void setTotalLicenses(Integer totalLicenses) {
		this.totalLicenses = totalLicenses;
	}

	/**
	 * @return the softwareName
	 */
	public String getSoftwareName() {
		return softwareName;
	}

	/**
	 * @param softwareName the softwareName to set
	 */
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	

    // ********************** Common Methods ********************** //

    /**
	 * @param systemName the systemName to set
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	/**
	 * @return the systemName
	 */
	public String getSystemName() {
		return systemName;
	}

	public String toString() {
        return  softwareName + ", " + getSystemName() + "\n" + getShortDescription();
    }
}