package org.gridchem.service.job.prediction;

import org.gridchem.service.beans.ComputeBean;
import org.gridchem.service.beans.QueueBean;

public class QbetsPrediction implements Comparable<QbetsPrediction>{
    ComputeBean machine;
    QueueBean queue;
    double confidenceInterval;
    
    public QbetsPrediction(ComputeBean machine, QueueBean queue, double prediction) {
      this.machine = machine;
      this.queue = queue;
      this.confidenceInterval = prediction;
    }

    public ComputeBean getMachine() {
		return machine;
	}

	public void setMachine(ComputeBean machine) {
		this.machine = machine;
	}

	public QueueBean getQueue() {
		return queue;
	}

	public void setQueue(QueueBean queue) {
		this.queue = queue;
	}

	public double getConfidenceInterval() {
		return confidenceInterval;
	}

	public void setConfidenceInterval(double confidenceInterval) {
		this.confidenceInterval = confidenceInterval;
	}

	public int compareTo(QbetsPrediction o) {
        if (o instanceof QbetsPrediction) {
            return ((confidenceInterval > o.confidenceInterval)?1:0);
        }
        return 0;
    }
    
    
}
