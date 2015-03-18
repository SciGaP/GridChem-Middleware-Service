/* 
 * Created on May 22, 2008
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.gridchem.service.job.prediction.qbets;

import java.util.List;

import org.gridchem.service.beans.QueueBean;
import org.jdom.Element;
import org.jdom.Namespace;


public class MachineParser implements GenericParser {
    private MachineBean bean = null;

    Element elMachine;
    Namespace nsMachine;
    
    public MachineParser(Element element) {
        bean = new MachineBean();
        elMachine = element;
        parse(element);
    }
    
    public QbetsBean getBean() {
        return bean;
    }

    public void parse(Element element) {
        handleTag();
        handleQueues();
        handleLoginHosts();
        handleLabel();
    }
    
    protected void handleTag() {
        bean.setTag(elMachine.getChildTextTrim("tag", nsMachine));
    }
    
    @SuppressWarnings("unchecked")
	protected void handleQueues() {
        List<Element> elQueueList = elMachine.getChild("queues").getChildren("queue",nsMachine);
        
        for (Element elQueue: elQueueList) {
            QueueBean qBean = new QueueBean();
            qBean.setDefaultQueue(Integer.valueOf(elQueue.getAttribute("default").getValue()) == 1);
            qBean.setName(elQueue.getText());
            bean.getQueues().add(qBean);
        }
    }
    
    @SuppressWarnings("unchecked")
	protected void handleLoginHosts() {
        List<Element> elLoginHostList = elMachine.getChild("loginHosts").getChildren("host",nsMachine);
        
        for (Element elLoginHost: elLoginHostList) {
            bean.getLoginHosts().add(elLoginHost.getText());
        }
    }
   
    protected void handleLabel() {
        bean.setTag(elMachine.getChildTextTrim("label", nsMachine));
    }

}
