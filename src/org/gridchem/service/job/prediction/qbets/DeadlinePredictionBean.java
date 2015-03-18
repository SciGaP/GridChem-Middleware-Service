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
public class DeadlinePredictionBean extends AbstractResponseBean {
    protected String status;
    protected String statusLong;
    protected double prediction;
    
    public DeadlinePredictionBean() {
        this.responseType = QbetsProperties.DEADLINE_PREDICTION;
    }
    
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the statusLong
     */
    public String getStatusLong() {
        return statusLong;
    }
    /**
     * @param statusLong the statusLong to set
     */
    public void setStatusLong(String statusLong) {
        this.statusLong = statusLong;
    }
    /**
     * @return the prediction
     */
    public double getPrediction() {
        return prediction;
    }
    /**
     * @param prediction the prediction to set
     */
    public void setPrediction(double prediction) {
        this.prediction = prediction;
    }
    
    public String toString() {
        return "<deadlinePrediction>\n" + 
               "\t<status>" + status + "</status>\n" + 
               "\t<statusLong>" + statusLong + "</statusLong>\n" +
               "\t<prediction>" + status + "</prediction>\n" +
               "</deadlinePrediction>";
    }
}
