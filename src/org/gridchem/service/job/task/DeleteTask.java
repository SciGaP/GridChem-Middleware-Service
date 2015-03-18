/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Jun 30, 2006
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

package org.gridchem.service.job.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.JobBean;
import org.gridchem.service.dao.DaoFactory;
import org.gridchem.service.dao.JobDao;
import org.gridchem.service.exceptions.JobDeleteException;
import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.exceptions.JobKillException;
import org.gridchem.service.exceptions.JobSchedulingException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.model.Job;
import org.gridchem.service.model.enumeration.AccessType;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.gridchem.service.util.ServiceUtil;
import org.gridchem.service.util.Settings;

/**
 * Kill the given job by either removing it from the queue or killing a
 * running process.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class DeleteTask extends JobTaskImpl implements JobTask {
    public static Logger log = Logger.getLogger(DeleteTask.class.getName());
    
    boolean killed = false;
    boolean deletedInput = false;
    boolean deletedData = false;
    @SuppressWarnings("unused")
	private String key = "";

    /**
     * @param session
     * @param job
     */
    public DeleteTask(GMSSession session, Long jobId) {
        super(session, jobId);
    }


    /**
     * @param session
     * @param jobDTO
     * @param notifyUser
     */
    public DeleteTask(GMSSession session, JobBean jobDTO, boolean notifyUser) {
        super(session,jobDTO, notifyUser);
    }
    
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Delete the job from the user's perspective by setting it's 'deleted'
     * flag to true.  Delete all data for this job from mass storage.  Delete
     * the input data associated with this job.
     * 
     */
    public Job execute() throws JobException {        
        try {
            
            validate();
            
            log.info("Attempting to delete job [" + job.getId() + 
                    "]" + ": " + job.getName());
            
            // if the job is running, kill it first.
            if (job.getStatus().equals(JobStatusType.RUNNING) || 
                    job.getStatus().equals(JobStatusType.SCHEDULED) ||
                    job.getStatus().equals(JobStatusType.MIGRATING) ||
                    job.getStatus().equals(JobStatusType.INITIAL) ||
                    job.getStatus().equals(JobStatusType.UNKNOWN)) {
                KillTask kill = new KillTask(session,job.getId());
                try {
                    job = kill.execute();
                } catch(JobKillException e) {
                    throw new JobDeleteException("Attempting to delete job [" + job.getId() + 
                            "]" + " experienced a failure while attempting " + 
                            "to kill the job",e,e.getCgiOutput());
                }
                killed = true;
            } else {
                killed = true;
            }
            
            // then mark the job deleted.
            updateDeletedFlag();
            
            // then delete the data from mss
            try {
//                deleteData();
            } catch(Exception e) {
                log.error("Job deletion successful. " + 
                        "There was an error deleting job data:\n" + e.getMessage());
            }
            
            log.info("Job deletion complete.");
            
//            HibernateUtil.getSession().flush();
            
            return job;
            
        } catch (PermissionException e) {
            throw e;
        } catch (JobKillException e) {
            if (!deletedInput) {
                throw e;
            } else {
                log.error(e);
            }
        } catch (Exception e) {
            if (!deletedInput) {
                throw new JobDeleteException("Job deletion failed.", e, "");
            } else {
                log.error(e);
            }
        }
        
        return null;
    }
    
    public void validate() throws JobException,PermissionException {
        if (job == null) 
            throw new JobDeleteException("No job specified for deletion.");
        
        if (job.isDeleted()) {
            throw new JobDeleteException("Job " + job.getId() + " has already been deleted");
        }
        
//        log.debug("User " + user.getUserName() + " project " + user.getCurrentProject().getProjectId() + " job " + job.getProject());
        if (!job.getUser().getId().equals(session.getUserId())) {
            throw new PermissionException("User is not currently authenticated to this project. " + 
                    "Please logout and login again under the appropriate project " +
                    " to delete this job.");
        }
        
    }
    
    
    /**
     * Set the deleted flag to true and persist the job object.
     */
    private void updateDeletedFlag() throws Exception {
   
        job.setDeleted(true);
        job.setLastUpdated(new Date());
        
        JobDao.persist(job);
        
        deletedInput = true;
    }
    
//    /**
//     * Find the user's data and delete it from mass storage.
//     * @throws Exception
//     */
//    private void deleteData() throws Exception {
//        
//        URI remoteFile;
//        
//        String mss = "gsiftp://";
//        String slash = "/";
//        String dot = ".";
//        String jobDirectoryName = job.getName() + dot + 
//            job.getSystem().getName() + dot +
//            job.getLocalId() + dot +
//            new SimpleDateFormat("yyMMdd").format(job.getCreated());
//        
//        try {
//            
//            mss += job.getStorage().getHostname();
////                log.debug("Job has project " + up.getProject().getProjectName() + 
////                        " with mss located at: " + mss);
//            
//            String path = "";
////            log.debug("User has a " + job.getProject().getProjectType() + 
////                    " project.  Setting the path accordingly.");
//            if (!session.getType().equals(AccessType.COMMUNITY)) {
//            	path = job.getWorkDir() + slash + 
//                	job.getExperimentName() + slash + 
//                	jobDirectoryName;
//            } else {
//            	if (mss.indexOf("mss.ncsa.uiuc.edu") > -1) {
//                    path = "//u/ac/ccguser/external/";
//            	} else {
//            		path = "//u/ac/ccguser/internal/";
//            	}
//            	String username = new UserDao(session).get().getUserName();
//                
//            	path += username + slash + 
//	                job.getExperimentName() + slash + 
//	                jobDirectoryName;
//            }
//            
////            log.debug("Deleting directory: " + mss + path);
//            
////            remoteFile = new URI(mss + path);
//            log.debug("Deleting directory at uri: " + remoteFile);
////            FileManager fm = new FileManager(remoteFile,context,prefs);
//            
//            callDeleteServlet(remoteFile);
//            
//            deletedInput = false;
//            
//        } catch (PermissionException e) {
//            
//            throw new FileManagementException("Access to data denied.",e);
//            
//        } catch (URISyntaxException e) {
//            
//            throw new FileManagementException("Unable to load files.  Bad file uri.",e);
//            
//        } catch (Exception e) {
//            
//            throw new FileManagementException("Unable to delete files.",e);
//        }
//        
//    }
//    
//    private void callDeleteServlet(String uri) throws IOException, MalformedURLException {
//        try {
//            Properties args = new Properties();
//            args.put("action", "remove");
//            args.put("key", this.key);
//            args.put("uri", uri.toString());
//            
//            @SuppressWarnings("unused")
//			String argString = "";  // default
//
//            if (args != null) {
//              argString = "?" + toEncodedString(args);
//            }
//            
//            URL url = new URL("http://localhost:8080/servlets-examples/servlet/PersistentServlet" + args);
//            URLConnection conn = url.openConnection();
////            conn.setDoOutput(true);
////            conn.setDoInput(true);
//            conn.setUseCaches (false);
//            
////            BufferedWriter out = 
////                new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() ) );
////            out.write("action=remove");
////            out.write("key=" + GMSSession.getKey());
////            out.write("uri=" + path);
////            out.flush();
////            out.close();
//            BufferedReader in = 
//                new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
//            
//            String response;
//            String result = "";
//
//            while ( (response = in.readLine()) != null ) {
//                result += response;
//            }
//            in.close();
//            
////            if(Settings.VERBOSE) System.out.println(result);
//            
//            
//            if (result == null) {
//                throw new FileManagementException("Failed to delete " + uri.toString());
//            }
//            
//        }
//        catch ( MalformedURLException ex ) {
//            throw ex;
//        }
//        catch ( IOException ex ) {
//            throw ex;
//        }
//    }
    
 // Converts a Properties list to a URL-encoded query string
    @SuppressWarnings({ "unchecked", "unused" })
	private static String toEncodedString(Properties args) throws UnsupportedEncodingException {
      StringBuffer buf = new StringBuffer();
      Enumeration names = args.propertyNames();
      while (names.hasMoreElements()) {
        String name = (String) names.nextElement();
        String value = args.getProperty(name);
        buf.append(URLEncoder.encode(name,"UTF-8") + "=" + URLEncoder.encode(value,"UTF-8"));
        if (names.hasMoreElements()) buf.append("&");
      }
      return buf.toString();
    }

    /**
     * Call the CGI script to stop the given job.
     * 
     * @param cgiURL
     */
    @SuppressWarnings("unused")
	private void tryKillCGI(URL cgiURL) {
        String jobID = job.getLocalId();
        String mach = job.getSystem().getName();
        String line = "";
        
        // Send the above info to the CGI scripts to kill the job
        try
        {
            String line2;
            URLConnection connex = cgiURL.openConnection();
            connex.setDoOutput(true);
            PrintWriter outStream = new PrintWriter(connex.getOutputStream());
            String JoBID = URLEncoder.encode(jobID,"UTF-8");
            String sys = URLEncoder.encode(mach,"UTF-8");
            String userName;

            userName = URLEncoder.encode("ccguser","UTF-8");
            outStream.println("IsGridChem=" + URLEncoder.encode("true","UTF-8"));
            if (Settings.VERBOSE) log.info("ManageWindow:IsGridChem=" + "true");
            
            if (session.getType().equals(AccessType.COMMUNITY)) {
                userName = "ccguser";
                
            } else {
                // if an external user, get the user's local username from the db
                String localName = DaoFactory.getResourceDao(session).findLocalUsernameForComputeResource(job.getSystem().getName());
                
                // if none is found, throw an exception
                if (!ServiceUtil.isValid(localName) || localName.equals("ccguser")) {
                    throw new JobSchedulingException("Could not resolve local username. Job submission failed.");
                } 
         
                userName = localName;
                
            }
            
            outStream.println("JoBID="+JoBID);
            if (Settings.VERBOSE) log.info("Kill Job:killJob JoBID="+JoBID);
            outStream.println("Username="+userName);
            if (Settings.VERBOSE) log.info("Kill Job:killJob Username="+userName);
            outStream.println("GridChemUsername=" + userName);
            if (Settings.VERBOSE) log.info("Kill Job:GridChemUsername=" + userName);
            outStream.println("Sysnm="+sys);
            if (Settings.VERBOSE) log.info("Kill Job:killJob Sysnm="+sys);
            
            outStream.close();

            BufferedReader inStream = new BufferedReader(new 
                    InputStreamReader(connex.getInputStream()));
            
            while ((line2 = inStream.readLine()) != null) {
                int m = line2.length();
                    if (m > 0) {
                        line = line2;
                        if (Settings.VERBOSE) log.info(line);
                        if (line.startsWith("ERROR: jobid seems to NOT EXIST")) {
                            throw new JobException(line);
                        } else if (line.startsWith("llcancel: Usage:")) {
                            throw new JobException(line);
                        } else if (line.startsWith("kill_job__no_job_id_given_err")) {
                            throw new JobException("Job jobid found for the given job. Kill job failed.");
                        }
                }
            }
            
            job.setStatus(JobStatusType.STOPPED);
            
//            Session s = HibernateUtil.getSession();
//            Transaction tx = s.beginTransaction();
//            Job savedJob = (Job) s.get(Job.class, job.getId());
//            savedJob.setStatus(JobStatusType.STOPPED);
//            tx.commit();
//            
            JobDao.persist(job);
            
        } catch (JobException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JobException("Job kill failed.",e);
        } 
    }

}
