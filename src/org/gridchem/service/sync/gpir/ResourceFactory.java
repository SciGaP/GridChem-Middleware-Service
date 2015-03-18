/*
 * AbstractResourceBeanFactory.java
 *
 * Created on April 19, 2005, 2:59 PM
 */

package org.gridchem.service.sync.gpir;

import org.jdom.Element;

/**
 *
 * @author ericrobe
 */
public interface ResourceFactory {
    
    public AbstractResourceBean createBean();
    //public ResourceParser createParser();
    public GenericParser createParser(Element element);
}
