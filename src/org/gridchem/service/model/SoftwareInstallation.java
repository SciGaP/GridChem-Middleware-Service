/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Dec 15, 2005
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

import java.io.Serializable;
import java.util.Date;

import org.gridchem.service.beans.SoftwareInstallationBean;

/**
 * A SoftwareInstallation is the physical realization of a code on a resource.
 * The code is defined in abstract terms in the SoftwareResource class. This
 * class details how and where that code was built as well as any dependencies
 * the code maintains on a particular ComputeResource. 
 * 
 * Dependencies 
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 * @see Software ComputeResource
 */
public class SoftwareInstallation {
	
	// ******************* Begin Inner composite Id class ******************* //
	@SuppressWarnings("serial")
	public static class SoftwareInstallationId implements Serializable {
		private Long softwareId;
		private Long computeResourceId;
		
	    public SoftwareInstallationId() {}

		public SoftwareInstallationId(Long softwareResourceId, Long computeResourceId) {
			this.softwareId = softwareResourceId;
			this.computeResourceId = computeResourceId;
		}

		public boolean equals(Object o) {
			if (o instanceof SoftwareInstallationId) {
			    SoftwareInstallationId that = (SoftwareInstallationId)o;
				return this.softwareId.equals(that.softwareId) &&
					   this.computeResourceId.equals(that.computeResourceId);
			} else {
				return false;
			}
		}

		public int hashCode() {
			return softwareId.hashCode() + computeResourceId.hashCode();
		}
	}
	// ******************* End Inner composite Id class ******************* //

	private SoftwareInstallationId id = new SoftwareInstallationId();
    //private Long softwareInstallationId;
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
    private String appusage;
    private Integer totalLicenses;
    private String licenseInfo;
    private String licenseServer;
    private String licenseType;
    private String softwareName;
    private boolean valid = true;
    private Date lastUpdated = new Date();
    private Software software;
    private ComputeResource computeResource;
    
    /**
	 * 
	 */
	public SoftwareInstallation() {}
	
	public SoftwareInstallation(Software software, ComputeResource computeResource) {
		this.id = new SoftwareInstallationId(software.getId(), computeResource.getId());
		this.software = software;
		this.computeResource = computeResource;
	}
	
	
    // ********************** Accessor Methods ********************** //
    
	/**
	 * Get the software installation id
	 */
	public SoftwareInstallationId getId() {
		return this.id;
	}
	
	/** Set the software installation id
	 * 
	 * @return
	 */
    public void setId(SoftwareInstallationId id) {
    		this.id = id;
    }
	/**
     * @return Returns the arguments.
     */
    public String getArguments() {
        return arguments;
    }
    /**
     * @param arguments The arguments to set.
     */
    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
    
    /**
    * @return Returns the compileDate.
    */
   public Date getCompileDate() {
       return compileDate;
   }
   /**
    * @param compileDate The compileDate to set.
    */
   public void setCompileDate(Date compileDate) {
       this.compileDate = compileDate;
   }
   /**
    * @return Returns the compilerName.
    */
   public String getCompilerName() {
       return compilerName;
   }
   /**
    * @param compilerName The compilerName to set.
    */
   public void setCompilerName(String compilerName) {
       this.compilerName = compilerName;
   }

   /**
    * @return Returns the compilerVersion.
    */
   public String getCompilerVersion() {
       return compilerVersion;
   }
   /**
    * @param compilerVersion The compilerVersion to set.
    */
   public void setCompilerVersion(String compilerVersion) {
       this.compilerVersion = compilerVersion;
   }
 

   /**
    * @return Returns the executable.
    */
   public String getExecutablePath() {
       return this.executablePath;
   }
   /**
    * @param executable The executable to set.
    */
   public void setExecutablePath(String executablePath) {
       this.executablePath = executablePath;
   }
   /**
    * @return Returns the path to the startup script if any.
    */
   public String getScriptPath() {
       return this.scriptPath;
   }
   /**
    * @param executable The executable to set.
    */
   public void setScriptPath(String scriptPath) {
       this.scriptPath = scriptPath;
   }
   /**
    * @return Returns the pathName.
    */
   public String getHomeDir() {
       return this.homeDir;
   }
   /**
    * @param pathName The pathName to set.
    */
   public void setHomeDir(String homeDir) {
       this.homeDir = homeDir;
   }
   /**
    * @return Returns the startupEnvironment.
    */
   public String getStartupEnvironment() {
       return startupEnvironment;
   }
   /**
    * @param startupEnvironment The startupEnvironment to set.
    */
   public void setStartupEnvironment(String startupEnvironment) {
       this.startupEnvironment = startupEnvironment;
   }
   /**
    * @return Returns the appusage.
    */
   public String getUsage() {
       return appusage;
   }
   /**
    * @param usage The usage to set.
    */
   public void setUsage(String usage) {
       this.appusage = appusage;
   }
	/**
	 * @return Returns the softwareResource.
	 */
	public Software getSoftware() {
		return software;
	}
	/**
	 * @param softwareResources The softwareResources to set.
	 */
	public void setSoftware(Software software) {
		this.software = software;
	}
	
	/**
	 * @return Returns the computeResource.
	 */
	public ComputeResource getComputeResource() {
		return computeResource;
	}
	/**
	 * @param computeResource The computeResource to set.
	 */
	public void setComputeResource(ComputeResource computeResource) {
		this.computeResource = computeResource;
	}
	/**
	 * @return Returns the licenseInfo.
	 */
	public String getLicenseInfo() {
		return licenseInfo;
	}
	/**
	 * @param licenseInfo The licenseInfo to set.
	 */
	public void setLicenseInfo(String licenseInfo) {
		this.licenseInfo = licenseInfo;
	}
	/**
	 * @return Returns the licenseServer.
	 */
	public String getLicenseServer() {
		return licenseServer;
	}
	/**
	 * @param licenseServer The licenseServer to set.
	 */
	public void setLicenseServer(String licenseServer) {
		this.licenseServer = licenseServer;
	}
	/**
	 * @return Returns the totalLicenses.
	 */
	public Integer getTotalLicenses() {
		return totalLicenses;
	}
	/**
	 * @param totalLicenses The totalLicenses to set.
	 */
	public void setTotalLicenses(Integer totalLicenses) {
		this.totalLicenses = totalLicenses;
	}
	/**
	 * @return Returns the licenseType.
	 */
	public String getLicenseType() {
		return licenseType;
	}
	/**
	 * @param licenseType The licenseType to set.
	 */
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
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
	 * @return Returns the softwareName.
	 */
	public String getSoftwareName() {
		return softwareName;
	}
	/**
	 * @param softwareName The softwareName to set.
	 */
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	/**
	 * @return Returns the module.
	 */
	public String getModule() {
		return module;
	}
	/**
	 * @param module The module to set.
	 */
	public void setModule(String module) {
		this.module = module;
	}
    /**
     * @return Returns the lastUpdated.
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }
    /**
     * @param lastUpdated The lastUpdated to set.
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    /**
      * @return the valid
      */
     public boolean getValid() {
         return valid;
     }

     /**
      * @param valid the valid to set
      */
     public void setValid(boolean valid) {
         this.valid = valid;
     }
    
// ********************** Common Methods ********************** //

public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SoftwareInstallation)) return false;
		final SoftwareInstallation softwareInstallation = (SoftwareInstallation) o;
		if (!compileDate.equals(softwareInstallation.compileDate)) return false;
		if (!compilerName.equals(softwareInstallation.compilerName)) return false;
		if (!homeDir.equals(softwareInstallation.homeDir)) return false;
		if (!scriptPath.equals(softwareInstallation.scriptPath)) return false;
		if (!startupEnvironment.equals(softwareInstallation.startupEnvironment)) return false;
		if (!executablePath.equals(softwareInstallation.executablePath)) return false;
		if (!arguments.equals(softwareInstallation.arguments)) return false;
		if (!appusage.equals(softwareInstallation.appusage)) return false;
		if (!totalLicenses.equals(softwareInstallation.totalLicenses)) return false;
		if (!licenseInfo.equals(softwareInstallation.licenseInfo)) return false;
		if (!licenseServer.equals(softwareInstallation.licenseServer)) return false;
		if (!licenseType.equals(softwareInstallation.licenseType)) return false;
		return true;
	}
	
	public int hashCode() {
		return (new String(this.getSoftware().getName()+
				this.getComputeResource().getName() +
				this.executablePath)).hashCode();
	}
	
	public String toString() {
		return  "Software Installation: (" + getId() + ")\n" +
				"Software Name: '" + getSoftwareName() + "'" +
				"Compiler Name: '" + getCompilerName() + "'" +
				"Compiler Version: '" + getCompilerVersion() + "'" +
				"Compile Date: '" + getCompileDate() + "'" +
				"Application Path: '" + getHomeDir() + "'" +
				"Startup Environment: '" + getStartupEnvironment() + "'" +
				"Executable Name: '" + getExecutablePath() + "'" +
				"Script Path: '" + getScriptPath() + "'" +
				"Command Line Arguments: '" + getArguments() + "'" +
				"Usage: '" + getUsage() + "'" +
				"Number of Licenses: '" + getTotalLicenses() + "'" +
				"License Info: '" + getLicenseInfo() + "'" +
				"License Server: '" + getLicenseServer() + "'" +
				"License Type: '" + getLicenseType() + "'" + 
                "Valid: '" + valid + "'";
	}
	
	public int compareTo(Object o) {
		if (o instanceof SoftwareInstallation)
			return this.getCompileDate().compareTo(
					((SoftwareInstallation)o).getCompileDate() );
		return 0;
	}
	
	public SoftwareInstallationBean toBean() {
		SoftwareInstallationBean bean = new SoftwareInstallationBean();
		bean.setSoftwareName(getSoftwareName());
		bean.setSystemName(getComputeResource().getName());
		bean.setCompilerName(compilerName);
		bean.setCompilerVersion(compilerVersion);
		bean.setCompileDate(compileDate);
		bean.setHomeDir(homeDir);
		bean.setStartupEnvironment(startupEnvironment);
		bean.setExecutablePath(executablePath);
		bean.setScriptPath(scriptPath);
		bean.setArguments(arguments);
		bean.setUsage(appusage);
		bean.setTotalLicenses(totalLicenses);
		
		return bean;
	}
	
}
