/*
 * GpirResource.java
 *
 * Created on April 19, 2005, 3:40 PM
 */

package org.gridchem.service.wsclients.gpir;

import org.jdom.Element;

/**
 *
 * @author ericrobe
 */
public class Resource { 
    GenericParser parser;
    GPIRBean bean;
    ResourceFactory resourceFactory;
    
    public Resource(ResourceFactory factory) {
        this.resourceFactory = factory;
    }
    
    public void build(Element element) {
        parser = resourceFactory.createParser(element);
        bean = parser.getBean(); 
    }
    
    public GPIRBean getBean() {
        return bean;
    }
    
}
