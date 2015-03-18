/*
 * GpirResource.java
 *
 * Created on April 19, 2005, 3:40 PM
 */

package org.gridchem.service.sync.gpir;

import org.jdom.Element;

/**
 *
 * @author Rion Dooley
 */
public abstract class AbstractJob {
    JobParser parser;
    JobBean bean;
    
    abstract void build(Element element);
    
    public JobBean getBean() {
        return bean;
    }
    
}
