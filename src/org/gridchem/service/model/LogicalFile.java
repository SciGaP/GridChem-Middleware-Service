/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Mar 15, 2007
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

import java.io.File;
import java.util.Date;
import java.util.Random;

import org.gridchem.service.beans.LogicalFileBean;
import org.gridchem.service.dao.LogicalFileDao;


/**
 * Logical file reference persisted in the db for association with
 * a given job.  Files are uploaded and persisted, then binded to a 
 * job record after successful job submission.  In this way, we can
 * purge cached files by checking their timestamp and if they're 
 * associated with a job.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class LogicalFile {
    private Long id;
    private Long userId;
    private String uuid;
    private String slocalPath; // GMS Server side local path
    private String localPath; // local to client system
//    private String userPath;
    private Date created = new Date();
    private Date lastUpdated = new Date();
    private String remotePath;
    private long jobId;
    private long length = 0;
    
    public LogicalFile() {}
    
    public LogicalFile(LogicalFileBean bean) {
    	this.id = bean.getId();
    	this.userId = bean.getUserId();
        this.slocalPath = bean.getSlocalPath();
        this.localPath = bean.getLocalPath();
        this.remotePath = bean.getRemotePath();
        this.created = bean.getCreated();
        this.lastUpdated = bean.getLastUpdated();
        this.uuid = bean.getUuid();
        this.jobId = bean.getJobId();
        this.length = bean.getLength();
    }
    
    public LogicalFile(Long userId, String userPath, File localFile, String remotePath, long jobId) {
        this.userId = userId;
        //this.localPath = userPath; //localFile.getLAbsolutePath();
        Date currdate = new Date();
        String clientpath="";
        //if (this.created.compareTo(null)>0){
        this.localPath = localFile.getAbsolutePath(); //localFile.getLAbsolutePath();
        clientpath = this.localPath;
        System.out.println("Values of client local path set as "+clientpath);
        //}
        this.slocalPath = localFile.getAbsolutePath();
        this.localPath = clientpath;
        System.out.println("Values of local and slocal path: "+clientpath+" slocal path "+this.slocalPath);
        this.remotePath = remotePath;
        this.created = new Date();
        this.lastUpdated = new Date();
        this.uuid = localFile.getName() + "." + 
            ((System.currentTimeMillis() * 100) + "." + 
            (100 + (Math.abs(new Random().nextInt()) % 1000)));
        this.jobId = jobId;
        this.length = localFile.length();
    }
    
    /**
     * @return the jobId
     */
    public long getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    /**
     * @return the userId of the owner
     */
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
    	this.userId = userId;
    }
    
    /**
     * @param userId of the owner
     */
    public void setUserSessionKey(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

//    /**
//     * @return the userPath
//     */
//    public String getUserPath() {
//        return userPath;
//    }
//
//    /**
//     * @param userPath the userPath to set
//     */
//    public void setUserPath(String userPath) {
//        this.userPath = userPath;
//    }

    /**
     * @return the localPath sent by client
     */
    public String getLocalPath() {
        return localPath;
    }

       /**
     * @return the slocalPath at GMS Server
     */
    public String getSlocalPath() {
        return slocalPath;
    }



    /**
     * @param localPath the localPath to set
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
    /**
     * @param slocalPath the slocalPath to set
     */
    public void setSlocalPath(String slocalPath) {
        this.slocalPath = slocalPath;
    }


    /**
     * @return the remotePath
     */
    public String getRemotePath() {
        return remotePath;
    }

    /**
     * @param remotePath the remotePath to set
     */
    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }
    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }
    /**
     * @return the fileID
     */
    public Long getId() {
        return id;
    }
    /**
     * @param fileID the fileID to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the length
     */
    public long getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }
    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    
    //  ********************** Common Methods ********************** //

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProject)) return false;
        final LogicalFile file = (LogicalFile) o;
        if (!id.equals(file.getId())) return false;
        if (!uuid.equals(file.getUuid())) return false;
        return true;
    }
    
    public int compareTo(Object o) {
        // CategorizedProjects are sorted by date
        if (o instanceof LogicalFile)
            return this.localPath.compareTo(((LogicalFile)o).localPath);
        return 0;
    }

    public String toString() {
        return  "UUID: " + getUuid() + ", " +
                "Server Local name: '" + getSlocalPath() + "'" +
                "Client Local name: '" + getLocalPath() + "'" +
                "Remote name: '" + getRemotePath() +  "'" +
                "Updated: '" + getLastUpdated()  + "'";
    }

    public LogicalFileBean toBean() {
    	LogicalFileBean bean = new LogicalFileBean();
    	bean.setId(id);
    	bean.setUserId(userId);
    	bean.setUuid(uuid);
    	bean.setSlocalPath(slocalPath);
    	bean.setLocalPath(localPath);
//    	bean.setUserPath(userPath);
    	bean.setRemotePath(remotePath);
    	bean.setCreated(created);
    	bean.setLastUpdated(lastUpdated);
    	bean.setJobId(jobId);
    	bean.setLength(length);
    	
    	return bean;
    }
    // ********************** Business Methods ********************** //
    
    public void save() {
        LogicalFileDao.persist(this);
    }
}
