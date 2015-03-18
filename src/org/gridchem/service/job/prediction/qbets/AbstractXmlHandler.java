/* 
 * Created on May 22, 2008
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.gridchem.service.job.prediction.qbets;

/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public abstract class AbstractXmlHandler implements XmlHandler {

    QbetsCollection beans;
    String strXml;
    
    /** Creates a new instance of AbstractXmlHandler */
    public AbstractXmlHandler(String xml) {
        this.strXml = xml;
        beans = null; 
    }
    
    /* (non-Javadoc)
     * @see org.gridchem.service.gms.synch.qbets.XmlHandler#getBeans()
     */
    public QbetsCollection getBeans() {
        // TODO Auto-generated method stub
        return null;
    }

}
