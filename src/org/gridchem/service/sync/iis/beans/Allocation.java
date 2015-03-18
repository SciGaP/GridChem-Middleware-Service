/**
 * 
 */
package org.gridchem.service.sync.iis.beans;

import java.math.BigDecimal;
import java.util.Date;

import org.gridchem.service.beans.ProjectBean;
import org.gridchem.service.beans.UsageBean;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.model.enumeration.ProjectStatusType;


/**
 * @author dooley
 *
 */
public class Allocation implements TgcdbDTO {

	private String projectId;
	private String projectTitle;
	private Integer allocationId;
	private Date startDate;
	private Date endDate;
	private BigDecimal baseAllocation;
	private BigDecimal remainingAllocation;
	private String allocResourceName;
	private String projState;
	private String piLastName;
	private String piFirstName;
	private Integer personId;
	private String firstName;
	private String lastName;
	private Boolean isPi;
	private BigDecimal usedAllocation;
	private String acctState;
	

	public Allocation() {
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public Integer getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(Integer allocationId) {
		this.allocationId = allocationId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getBaseAllocation() {
		return baseAllocation;
	}

	public void setBaseAllocation(BigDecimal baseAllocation) {
		this.baseAllocation = baseAllocation;
	}

	public BigDecimal getRemainingAllocation() {
		return remainingAllocation;
	}

	public void setRemainingAllocation(BigDecimal remainingAllocation) {
		this.remainingAllocation = remainingAllocation;
	}

	public String getAllocResourceName() {
		return allocResourceName;
	}

	public void setAllocResourceName(String allocResourceName) {
		this.allocResourceName = allocResourceName;
	}

	public String getProjState() {
		return projState;
	}

	public void setProjState(String projState) {
		this.projState = projState;
	}

	public String getPiLastName() {
		return piLastName;
	}

	public void setPiLastName(String piLastName) {
		this.piLastName = piLastName;
	}

	public String getPiFirstName() {
		return piFirstName;
	}

	public void setPiFirstName(String piFirstName) {
		this.piFirstName = piFirstName;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getIsPi() {
		return isPi;
	}

	public void setIsPi(Boolean isPi) {
		this.isPi = isPi;
	}

	public BigDecimal getUsedAllocation() {
		return usedAllocation;
	}

	public void setUsedAllocation(BigDecimal usedAllocation) {
		this.usedAllocation = usedAllocation;
	}

	public String getAcctState() {
		return acctState;
	}

	public void setAcctState(String acctState) {
		this.acctState = acctState;
	}
	
	public ProjectBean toBean() {
		ProjectBean bean = new ProjectBean();
		bean.setName(projectId);
		bean.setDescription(projectTitle);
		bean.setStartDate(startDate);
		bean.setEndDate(endDate);
		bean.setCreated(startDate);
		bean.setLastUpdated(startDate);
		bean.setStatus(ProjectStatusType.valueOf(acctState.toUpperCase()));
		bean.setType(AccessType.TERAGRID);
		bean.setPi(piFirstName + " " + piLastName);
		UsageBean usageBean = new UsageBean();
		usageBean.setAllocated(baseAllocation.doubleValue());
		usageBean.setUsed(usedAllocation.doubleValue());
		usageBean.setBalance(remainingAllocation.doubleValue());
		bean.setUsage(usageBean);
		
		return bean;
	}
}
