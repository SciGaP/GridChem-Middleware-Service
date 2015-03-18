/* 
 * Created on May 22, 2008
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.gridchem.service.job.prediction.qbets;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class ResponseList implements QbetsCollection {
    ArrayList<AbstractResponseBean> list;
    
    public ResponseList() {
        list = new ArrayList<AbstractResponseBean>();
    }
    
    /* (non-Javadoc)
     * @see org.gridchem.service.gms.synch.qbets.QbetsCollection#get(java.lang.String)
     */
    public List<AbstractResponseBean> get(String type) {
        ArrayList<AbstractResponseBean> myBeans = new ArrayList<AbstractResponseBean>();
        for(AbstractResponseBean bean: list) {
            if (bean.getResponseType().equals(type)) {
                myBeans.add(bean);
            }
        }
        return myBeans;
    }

    /* (non-Javadoc)
     * @see org.gridchem.service.gms.synch.qbets.QbetsCollection#put(org.gridchem.service.gms.synch.qbets.QbetsBean)
     */
    public boolean put(QbetsBean bean) {
        // TODO Auto-generated method stub
        return false;
    }

}
