/* 
 * Created on May 22, 2008
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.gridchem.service.job.prediction.qbets;

import org.jdom.Element;

/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public interface ResponseFactory {
    public AbstractResponseBean createBean();
    public GenericParser createParser(Element element);

}
