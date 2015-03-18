package org.gridchem.service.job.prediction;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.rpc.ServiceException;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.JobBean;
import org.gridchem.service.beans.QueueBean;
import org.gridchem.service.dao.DaoFactory;
import org.gridchem.service.exceptions.ProviderException;
import org.gridchem.service.job.prediction.qbets.DeadlinePredictionBean;
import org.gridchem.service.job.prediction.qbets.DeadlinePredictionXmlHandler;
import org.gridchem.service.job.prediction.qbets.MachineBean;
import org.gridchem.service.job.prediction.qbets.MachineXmlHandler;
import org.gridchem.service.job.prediction.qbets.QbetsCollection;
import org.gridchem.service.job.prediction.qbets.QbetsProperties;
import org.gridchem.service.job.prediction.qbets.XmlHandler;
import org.gridchem.service.model.GMSSession;
import org.gridchem.service.util.Settings;

import cs.ucsb.edu.NwsServiceLocator;
import cs.ucsb.edu.NwsSoapBindingStub;

public class Qbets {
	

	/**
     * Query Rich Wolski's TG queue prediction service to find 
     * quickest start time in the CCG.
     * 
     * @param serviceURL
     * @return
     * @throws RemoteException 
	 * @throws ProviderException 
     */
    public static QbetsPrediction findFirstAvailableResource(GMSSession session, JobBean job) throws ServiceException, RemoteException, ProviderException {
        
        NwsServiceLocator l = new NwsServiceLocator();
        NwsSoapBindingStub stub = (NwsSoapBindingStub)  l.getNws();
        

//        System.out.println(stub.qbetsPredictBound(0, job.getComputeResource().getName().toLowerCase(), job.getQueue().getName(), job.getRequestedCpus().intValue(), job.getRequestedCpuTime().getTimeInMillis(), 0, 0));
        // Here we make a call to the service querying for the probability that the job will
        // start within the next 2 hours.  We must make this call for each resource/queue combination

        // Get the TG machine list
        String machineXml = stub.qbetsGetMachines();
        System.out.println(machineXml);
        QbetsCollection collection = null;
        XmlHandler handler = null;
        PredictionCollection predictions = new PredictionCollection();
        
        QbetsPrediction prediction = null;
        
        if (machineXml != null) {
            handler = new MachineXmlHandler(machineXml);
            collection = handler.getBeans();
            
            // resolve the beans to the compute resources in the ccg.
            for (Object bean: collection.get(QbetsProperties.MACHINE)) {
                MachineBean mBean = (MachineBean)bean;
                boolean found = false;
                
                for (Object o: DaoFactory.getSoftwareDao(session).getInstallationsForSoftware(job.getSoftwareName())) {
                    ComputeBean hpc = (ComputeBean)o;
                    // if the machine bean represts a ccg resource, resolve the queues
                    if (hpc.getName().toLowerCase().equals(mBean.getTag())) {
                        
                        found = true;
                        
                        for (QueueBean qBean: mBean.getQueues()) {
                            for (QueueBean q: hpc.getQueues()) {
                                // if the machine bean and queue bean match an available resource
                                // get a prediction on this combination.
                                if (q.getName().toLowerCase().equals(qBean.getName())) {
                                    String deadlineXml = stub.qbetsPredictDeadline(
                                            0,                                           // timestamp of when we want the job to start 
                                            mBean.getTag(),                              // internal tag indicating machine/site name
                                            qBean.getName(),                             // queue of interest on specified machine
                                            job.getRequestedCpus().intValue(),           // number of nodes the job will request
                                            job.getRequestedCpuTime().getTimeInMillis(), // max willtime the job will request
                                            0,                                           // confidence interval around quantile prediction - default to .95
                                            Settings.DEFAULT_JOB_START_TIME*60*60);      // seconds from now when the job has to start
                                    XmlHandler deadlineHandler = new DeadlinePredictionXmlHandler(deadlineXml);
                                    QbetsCollection deadlineCollection = deadlineHandler.getBeans();
                                    DeadlinePredictionBean predictionBean = (DeadlinePredictionBean)deadlineCollection.get(QbetsProperties.DEADLINE_PREDICTION).get(0);
                                    predictions.add(hpc, q, predictionBean.getPrediction());
                                    break;
                                }
                            }
                        }
                        
                        if (found)
                            break;
                    }
                }
            }
            
            prediction = predictions.getBest();
            if (prediction != null) {
                job.setSystemName(prediction.machine.getName());
                job.setQueueName(prediction.getQueue().getName());
            }
        }
        
        return prediction;
        
    }
}

class PredictionCollection {
    ArrayList<QbetsPrediction> predictions = null;
    
    public PredictionCollection(){
        predictions = new ArrayList<QbetsPrediction>();
    }
    
    public void add(ComputeBean machine, QueueBean queue, double prediction) {
        predictions.add(new QbetsPrediction(machine, queue, prediction));
    }
    
	@SuppressWarnings("unused")
	private void sort() {
        java.util.Collections.sort(predictions);
    }
    
    public QbetsPrediction getBest() {
        if (predictions.size() > 0) {
            return predictions.get(0);
        } else {
            return null;
        }
    }
        
    
}