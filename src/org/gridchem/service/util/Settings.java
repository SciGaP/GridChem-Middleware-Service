/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 18, 2005
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

package org.gridchem.service.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Settings for GMS service...should be saved in a file or DB. Maybe
 * use an properties file or xml parser to read this in.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 */
public class Settings {

    public static final Properties props = new Properties();
    static {
        try {
            props.load(Settings.class.getClassLoader().getResource("gms.properties").openStream());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /******************* Community User Auth Settings ********************/
    
    public static final String COMMUNITY_USERNAME       = props.getProperty("comm.username");
    
    public static final String COMMUNITY_PASSWORD       = props.getProperty("comm.passwd");
    
    /******************* Service Settings ********************/
    
    public static final int REFRESH_RATE                = new Integer(props.getProperty("refresh.rate")).intValue();; /* interval for updating db from web service*/
    
    public static final int JOB_NOTIFICATION_RATE       = new Integer(props.getProperty("job.notification.rate")).intValue();; /* interval for checking for job status changes and sending notifications */
    
    public static final boolean DEBUG                   = ((props.getProperty("log.debug").equals(""))?false:true);  /* log error messages */
    
    public static final boolean VERBOSE                 = ((props.getProperty("log.verbose").equals(""))?false:true); /* enable verbose output */
    
    public static final int MAX_JOBS_RETURNED           = new Integer(props.getProperty("max.jobs.returned")).intValue();
	
    public static final int DEFAULT_JOB_START_TIME		= new Integer(props.getProperty("default.prediction.job.start.time")).intValue();
    
    public static final String API_KEY 					= props.getProperty("api.key"); /* api key needed by external services wishing to call the trigger service */
    
    /******************* Third Party Service Locations ******************/
    
    public static final String CCG_MYPROXY_SERVER       = props.getProperty("ccg.myproxy.server");
    
    public static final int CCG_MYPROXY_PORT            = new Integer(props.getProperty("ccg.myproxy.port")).intValue();
    
    public static final String TERAGRID_MYPROXY_SERVER       = props.getProperty("teragrid.myproxy.server");
    
    public static final int TERAGRID_MYPROXY_PORT            = new Integer(props.getProperty("teragrid.myproxy.port")).intValue();
    
    public static final String GPIR_SERVER         		= props.getProperty("gpir.server");
    
    public static final String MASS_STORAGE_SERVER      = props.getProperty("mss.server");

    public static final String CCG_CGI_SERVER           = props.getProperty("cgi.server");
    
    public static final String GRMS_SERVER              = props.getProperty("grms.server");
    
    public static final String GMS_DN                   = props.getProperty("gms.dn");
    
    public static final String AIM_SERVER               = props.getProperty("im.server");
    
    public static final int AIM_PORT                    = new Integer(props.getProperty("im.port")).intValue();
    
    public static final String MAIL_SERVER              = props.getProperty("mail.smtps.host");
    
    public static final String TERAGRID_PROFILE_SERVER   = props.getProperty("teragrid.profile.host");
    
    public static final String TERAGRID_WEBMDS_SERVER   = props.getProperty("teragrid.webmds.host");
    
    public static final String INCA_SERVICE_URL         = "http://" + props.getProperty("inca.server");
    
    public static final String IIS_SERVICE_URL         = "http://" + props.getProperty("iis.server");
    
    public static final String KIT_SERVICE_URL         = "http://" + props.getProperty("kit.server");
    
    /******************* Service Configuration Options *******/
    
    public static final String PROXY_DIR                = props.getProperty("proxy.dir");;  /* directory containing user proxies */
    
    public static final String TEMP_DATA_DIR            = props.getProperty("home.dir");  /* directory to store cached files from mss */
    
    public static final String VO                       = props.getProperty("vo.name");  /* VO for which to query GPIR */
    
    public static final String GRMS_DN                  = props.getProperty("grms.dn");  /* VO for which to query GPIR */
    
    /******************* External Authentication Options *******/
    
    public static final String AIMLOGIN                	= props.getProperty("im.handle");  /* directory containing user proxies */
    
    public static final String AIMPASSWORD            	= props.getProperty("im.password");  /* directory to store cached files from mss */
     
    public static final String MAILLOGIN                = props.getProperty("mail.smtps.user");;  /* service email account */
    
    public static final String MAILPASSWORD             = props.getProperty("mail.smtps.passwd");  /* service email password */
    
    public static final String MAILSMTPSPROTOCOL        = props.getProperty("mail.smtps.auth");  /* service email password */

	public static final String TWITTER_USERNAME 		= props.getProperty("twitter.user");  /* twitter username */
	
	public static final String TWITTER_PASSWORD 		= props.getProperty("twitter.passwd");  /* twitter password */
    
}
