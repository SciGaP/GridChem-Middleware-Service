/*
 * GpirResource.java
 *
 * Created on April 19, 2005, 3:40 PM
 */

package org.gridchem.service.sync.gpir;

import org.jdom.Element;

/**
 *
 * @author ericrobe
 */
public abstract class AbstractResource {
    GenericParser parser;
    AbstractResourceBean bean;
    
    abstract void build(Element element);
    
    public AbstractResourceBean getBean() {
        return bean;
    }
    
}
