/*
 * SummaryResourceCollection.java
 *
 * Created on April 26, 2005, 11:00 AM
 */

package org.gridchem.service.sync.gpir;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ericrobe
 */
@SuppressWarnings({"unchecked"})
public class JobList implements JobCollection {
    ArrayList list;
    
    /** Creates a new instance of SummaryResourceCollection */
    public JobList() {
        list = new ArrayList();
    }
    
    public List get(String type) {
        return list;
    }

    public boolean put(GPIRBean bean) {
        return list.add(bean);
    }
    
}
