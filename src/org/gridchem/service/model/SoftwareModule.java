/**
 * 
 */
package org.gridchem.service.model;

/**
 * Module associated with a software package.  Not sure of the exact relationship. I just
 * saw these in the client and realized they shoudl be in the db.
 * 
 * @author dooley
 *
 */
public class SoftwareModule {

	private Long id;
	private String name;
	private Software software;
	
	public SoftwareModule() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}
	
	public boolean equals(Object o) {
		if (o instanceof SoftwareModule) {
			return ((SoftwareModule) o).getName().equals(name);
		}
		return false;
	}
	
	public String toString() {
		return name;
	}
}
