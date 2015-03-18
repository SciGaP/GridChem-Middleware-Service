/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Apr 13, 2007
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

package org.gridchem.service.job;

import java.io.StringReader;
import java.util.Calendar;
import java.util.Iterator;

import org.gridchem.service.exceptions.JobException;
import org.gridchem.service.model.enumeration.JobStatusType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

/**
 * Utility class to parse the RUS xml returned from the bhist cgi
 * script and give various job info.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class RusParser {

    private static Document doc;
    private static Element elRoot;
    private static Namespace nsRoot;
    
    @SuppressWarnings("unchecked")
	public static JobStatusType getStatus(String jobXml) throws JobException {
        if(jobXml == null) {
            System.out.println("Job status XML was null");
            throw new JobException("No job status information available.");
        }
        
        SAXBuilder builder = new SAXBuilder();
        StringReader xmlResultStream = new StringReader(jobXml);
        System.out.println("Job xml is:\n" + jobXml + "\n\n");
        
        try {
            doc = builder.build(xmlResultStream);
        }catch(Exception e) {
            throw new JobException("Unable to parse job status information",e);
        }
        
        elRoot = doc.getRootElement();
        nsRoot = elRoot.getNamespace();
        
//        <UsageRecord xmlns="http://schema.ogf.org/urf/2003/09/urf"
//            xmlns:urf="http://schema.ogf.org/urf/2003/09/urf"
//            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//            xsi:schemaLocation="http://schema.ogf.org/urf/2003/09/urf
//            file:/Users/jdoe/Documents/GGF/URWG/urwg-schema.09.02.xsd">
//         <RecordIdentity urf:recordId="http://www.gridchem.org/lonestar.tacc.utexas.edu.205484" urf:createTime="2007-04-13T11:22:31Z" />
//            <Status>Failed</Status>
//            <urf:CpuDuration>PT1S</urf:CpuDuration>
//            <urf:WallDuration>PT1M30S</urf:WallDuration>
//            <Charge>0.3</Charge>
//         </UsageRecord>
        
        
        @SuppressWarnings("unused")
		Calendar time = null;
        for(Iterator<Element> iter = elRoot.getChildren().iterator(); iter.hasNext();) {
            Element e = (Element)iter.next();
            
            System.out.println("Child node " + e.getName());
        }
        
        String statusString = elRoot.getChild("Status",nsRoot).getValue();
        System.out.println("Status is " + statusString);
        JobStatusType status = resolveStatus(statusString);
        
        String cpuDuration = elRoot.getChild("CpuDuration",nsRoot).getValue();
        System.out.println("CpuDuration is " + cpuDuration);
        if (cpuDuration != null && !cpuDuration.equals("")) {
            time = parseTime(cpuDuration);
        }
        
        String wallDuration = elRoot.getChild("WallDuration",nsRoot).getValue();
        System.out.println("WallDuration is " + wallDuration);
        if (wallDuration != null && !wallDuration.equals("")) {
        	time = parseTime(wallDuration);
        }
        
        String chargeString = elRoot.getChild("Charge",nsRoot).getValue();
        System.out.println("Charge is " + chargeString);
//        if (chargeString != null && !chargeString.equals("")) {
//            double charge = new Double(chargeString).doubleValue();
//        }
        
        return status;
    }
    
    private static JobStatusType resolveStatus(String statusString) {
        
    	JobStatusType status = JobStatusType.UNKNOWN;
        
        if (statusString.equals("Failed")) {
          status = JobStatusType.FAILED;  
        } else if (statusString.equals("Completed")) {
            status = JobStatusType.FINISHED;  
        } else if (statusString.equals("Started")) {
            status = JobStatusType.RUNNING;
        }   
        
        return status;
    }
    
    private static Calendar parseTime(String time) {
        
        Calendar cal = Calendar.getInstance();
        cal.clear();
        String value;
        int num;
        time = time.split("PT")[1];
        
        // format is PT[[0-9]*D][[0-9]*H][[0-9]*M][[0-9]*S]
        if (time.indexOf("D") > -1) {
            value = time.split("D")[0];
            value = time.split("D")[1];
            num = new Integer(value).intValue();
            cal.add(Calendar.DAY_OF_YEAR, num);
        }
        
        if (time.indexOf("H") > -1) {
            value = time.split("H")[0];
            time = time.split("H")[1];
            num = new Integer(value).intValue();
            cal.add(Calendar.HOUR_OF_DAY, num);
        }
        
        if (time.indexOf("M") > -1) {
            value = time.split("M")[0];
            time = time.split("M")[1];
            num = new Integer(value).intValue();
            cal.add(Calendar.MINUTE, num);
        }
        
        if (time.indexOf("S") > -1) {
            value = time.split("S")[0];
            num = new Integer(value).intValue();
            cal.add(Calendar.SECOND, num);
        }
        
        return cal;
    }
    
}
