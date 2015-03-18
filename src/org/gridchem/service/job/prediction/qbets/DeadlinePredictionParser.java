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
import org.jdom.Namespace;

/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class DeadlinePredictionParser implements GenericParser {
    private DeadlinePredictionBean dpBean = null;
    
    Element elDeadlinePrediction;
    Namespace nsDeadlinePrediction;
    
    public DeadlinePredictionParser(Element element) {
        this.dpBean = new DeadlinePredictionBean();
        this.elDeadlinePrediction = element;
        parse(element);
    }
    
    public QbetsBean getBean() {
        return dpBean;
    }

    public void parse(Element element) {
        handleStatus();
        handleStatusLong();
        handlePrediction();
    }
    
    protected void handleStatus() {
        dpBean.setStatus(elDeadlinePrediction.getChildTextTrim("status", nsDeadlinePrediction));
    }
    
    protected void handleStatusLong() {
        dpBean.setStatusLong(elDeadlinePrediction.getChildTextTrim("statusLong", nsDeadlinePrediction));
    }
    
    protected void handlePrediction() {
        dpBean.setPrediction(new Long(elDeadlinePrediction.getChildTextTrim("prediction", nsDeadlinePrediction)));
    }

}
