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

public class Response {
    GenericParser parser;
    QbetsBean bean;
    ResponseFactory factory;
    
    public Response(ResponseFactory factory) {
        this.factory = factory;
    }
    
    public void build(Element element) {
        parser = factory.createParser(element);
        bean = parser.getBean(); 
    }
    
    public QbetsBean getBean() {
        return this.bean;
    }
}
