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

import org.gridchem.service.beans.QueueBean;

/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class MachineBean extends AbstractResponseBean {
    
    protected String tag;
    protected ArrayList<QueueBean> queues;
    protected ArrayList<String> loginHosts;
    protected String label;
    
    public MachineBean() {
        this.responseType = QbetsProperties.MACHINE;
        this.queues = new ArrayList<QueueBean>();
        this.loginHosts = new ArrayList<String>();
    }
    
    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }
    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    /**
     * @return the queues
     */
    public ArrayList<QueueBean> getQueues() {
        return queues;
    }
    /**
     * @param queues the queues to set
     */
    public void setQueues(ArrayList<QueueBean> queues) {
        this.queues = queues;
    }
    /**
     * @return the loginHosts
     */
    public ArrayList<String> getLoginHosts() {
        return loginHosts;
    }
    /**
     * @param loginHosts the loginHosts to set
     */
    public void setLoginHosts(ArrayList<String> loginHosts) {
        this.loginHosts = loginHosts;
    }
    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }
    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String toString() {
        String machineString = "<machine>\n";
        
        if (tag == null) {
            machineString += "<tag/>\n";
        } else {
            machineString += "\t<tag>" + tag + "</tag>\n";
        }
        
        if (queues.size() > 0) {
            machineString += "\t<queues>\n";
            for (QueueBean q: queues) {
                machineString += "\t\t" + q.toString() + "\n";
            }
            machineString += "\t</queues>\n";
        } else {
            machineString += "\t<queues/>\n";
        }
        
        if (loginHosts.size() > 0) {
            machineString += "\t<loginHosts>\n";
            for (String host: loginHosts) {
                machineString += "\t\t<host>" + host + "</host>\n";
            }
            machineString += "\t</loginHosts>\n";
        } else {
            machineString += "\t<loginHosts/>\n";
        }
        
        if (label == null) {
            machineString += "<label/>\n";
        } else {
            machineString += "\t<label>" + label + "</label>\n";
        }
        
        machineString += "</machine>";
        
        return machineString;
        
    }
}
