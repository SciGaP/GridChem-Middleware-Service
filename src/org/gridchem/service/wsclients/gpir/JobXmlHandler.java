/*
 * SummaryXmlHandler.java
 *
 * Created on April 19, 2005, 2:19 PM
 */

package org.gridchem.service.wsclients.gpir;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author ericrobe
 */
@SuppressWarnings({"unchecked"})
public class JobXmlHandler extends AbstractXmlHandler {
    
    private Document doc;
    private Element elRoot;
    private Namespace nsRoot;
    private List elResourceList;
    private List elJobList;
    private Element elJob;
    private Element elResource;
    
    //private ResourceBean currentBean;
    
    /** Creates a new instance of SummaryXmlHandler */
    public JobXmlHandler(String xml) {
        super(xml);
        //beans = new JobList();
    }
    
    private void iterate() {
        if(strXml == null) {
            System.out.println("String XML was null, returning...");
            beans = null;
            return;
        }
        SAXBuilder builder = new SAXBuilder();
        StringReader xmlResultStream = new StringReader(strXml);
        try {
            doc = builder.build(xmlResultStream);
        }catch(JDOMException e) {
            //e.printStackTrace();
            //TODO: is this the right way to handle this?
            beans = null;
            return;
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        elRoot = doc.getRootElement();
        nsRoot = elRoot.getNamespace();
        // the resource associated wiht the job is listed in the
        // hostname attribute of the Jobs tag.
        elResourceList = elRoot.getChildren("Jobs", nsRoot);
        Iterator crIter = elResourceList.iterator();
        
        // iterate through the jobs associated with each resource
        while (crIter.hasNext()) {
            elResource = (Element) crIter.next();
            
            if(elResource != null) {
                Namespace nsJob = elResource.getNamespace();
                elJobList = elResource.getChildren("Job", nsJob);
                Iterator jIter = elJobList.iterator();
                
                while (jIter.hasNext()) {
                    elJob = (Element) jIter.next();
                    Job job = new Job(elResource.getAttribute("hostname").getValue());
                    
                    job.build(elJob);
                    JobBean bean = job.getBean();
                    if (bean.getId() != null && 
                            !bean.getId().equals("") &&
                            bean.getOwner() != null &&
                            bean.getQueue() != null &&
                            !bean.getQueue().equals("")) {
//                            bean.getOwner().equals("ccguser")) {
                        beans.put(bean);
                    }
                }
            }
        }
    }
    
    public JobCollection getBeans() {
        if(beans == null) {
            beans = new JobList();
            iterate();
            return (JobCollection)beans;
        } else {
            return (JobCollection)beans;
        }
    }
}
