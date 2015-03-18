/*
 * SummaryXmlHandler.java
 *
 * Created on April 19, 2005, 2:19 PM
 */

package org.gridchem.service.sync.gpir;

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
@SuppressWarnings({"unchecked","unused"})
public class SummaryXmlHandler extends AbstractXmlHandler {
    
    private Document doc;
    private Element elRoot;
    private Namespace nsRoot;
    private List elResourceList;
    private Element elResource;
    private Namespace nsResource;
    //private ResourceBean currentBean;
    private ResourceFactory beanFactory;
    
    /** Creates a new instance of SummaryXmlHandler */
    public SummaryXmlHandler(String xml) {
        super(xml);
        //beans = new ResourceList();
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
        elResourceList = elRoot.getChildren("ComputeResource", nsRoot);
        Iterator crIter = elResourceList.iterator();
        
        while (crIter.hasNext()) {
            elResource = (Element) crIter.next();
            Resource resource = null;  
            if(parseResourceType().equals(GpirProperties.COMPUTE)) {
                resource = new Resource(new ComputeResourceFactory());
            } else if(parseResourceType().equals(GpirProperties.STORAGE)) {
                resource = new Resource(new StorageResourceFactory());
            } else if(parseResourceType().equals(GpirProperties.VIZ)) {
                resource = new Resource(new VizResourceFactory());
            } else if(parseResourceType().equals(GpirProperties.PC_GRID)) {
                resource = new Resource(new PcGridResourceFactory());
            }
            
            if(elResource != null) {
                resource.build(elResource);
                beans.put(resource.getBean());
            }
        }
    }
    
    public GPIRCollection getBeans() {
        if(beans == null) {
            beans = new ResourceList();
            iterate();
            return beans;
        } else {
            return beans;
        }
    }
    
    private String parseResourceType() {
        String resourceType = "";
        if(elRoot.getName().equals(GpirProperties.SUMMARY)) {
            resourceType = elResource.getChildTextTrim("ResourceType");
        }
        return resourceType;
    }
    
}
