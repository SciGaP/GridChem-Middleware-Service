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
 * This interface allows client to implement their own parsing Strategy for parsing XML documents.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public interface GenericParser {
    /**
     * Method that allows implementors of this interface to choose the algorithm for parsing given an XML Element object.  Follows the Strategy design pattern.
     * @param element XML <CODE>Element</CODE> object to parse
     */
    public void parse(Element element);
    /**
     * Returns a populated <CODE>AbstractJobBean</CODE>
     * @return Returns a populated AbstractJobBean object
     */
    public QbetsBean getBean();
}
