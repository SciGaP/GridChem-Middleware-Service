/**
 * 
 */
package org.gridchem.service.beans;

import java.util.Hashtable;

import org.gridchem.service.model.enumeration.JobSearchFilterType;
import static org.gridchem.service.model.enumeration.JobSearchParameterTypes.*;

/**
 * @author dooley
 *
 */
public class SearchBean {
	
	private Hashtable<String, SearchParameter> criteria = new Hashtable<String, SearchParameter>();
	
	public class SearchParameter {
		public JobSearchFilterType type;
		public String value;
		
		public SearchParameter(JobSearchFilterType type, String value) {
			this.type = type;
			this.value = value;
		}
		
	}
	
	public SearchBean() {
	}
	
	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(Hashtable<String, SearchParameter> criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the criteria
	 */
	public Hashtable<String, SearchParameter> getCriteria() {
		return criteria;
	}
	
	public SearchParameter getSearchParameter(String attribute) {
		return this.criteria.get(attribute);
	}
	
	public void addCriteria(String attribute, JobSearchFilterType type, String value) {
		
		if (this.criteria.containsKey(attribute)) {
			this.criteria.remove(attribute);
		}
		
		this.criteria.put(attribute, new SearchParameter(type, value));
	}
	
	public void removeCriteria(String attribute) {
		if (this.criteria.containsKey(attribute)) {
			this.criteria.remove(attribute);
		}
	}

}
