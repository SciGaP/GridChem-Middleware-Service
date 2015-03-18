/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Apr 4, 2006
 * 
 * Developed by: CCT, Center for Computation and Technology, 
 * 				NCSA, University of Illinois at Urbana-Champaign
 * 				OSC, Ohio Supercomputing Center
 * 				TACC, Texas Advanced Computing Center
 * 				UKy, University of Kentucky
 * 
 * https://www.gridchem.org/
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal with the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom 
 * the Software is furnished to do so, subject to the following conditions:
 * 1. Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimers.
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimers in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the names of Chemistry and Computational Biology Group , NCSA, 
 *    University of Illinois at Urbana-Champaign, nor the names of its contributors 
 *    may be used to endorse or promote products derived from this Software without 
 *    specific prior written permission.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  
 * IN NO EVENT SHALL THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS WITH THE SOFTWARE.
*/

package org.gridchem.service.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.gridchem.service.exceptions.SynchronizationException;
import org.gridchem.service.sync.gpir.GpirDiscoveryAdaptor;
/**
 * Basic timer class for synching with the various web services. 
 * This object is instantiated at service startup and executes 
 * every 'interval' minutes thereafter.
 * 
 * Code for this class was adapted from the Sun Developer's Site's 
 * Timer tutorial.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 * @see SynchTimer
 */
public class SyncTimer {
    public static Logger log = Logger.getLogger(SyncTimer.class.getName());
	
    private static Timer timer;
    private static SyncTimer syncTimer = null;
    
    private SyncTimer(int interval) {
        timer = new Timer();
        schedule(interval);
    }

    public static SyncTimer getInstance(int interval) {
        if (syncTimer == null) {
            syncTimer = new SyncTimer(interval);
        } 
        
        return syncTimer;
    }
    
    public void schedule(int interval) {
        timer.scheduleAtFixedRate(new UpdateTask(),
                interval*60*1000,        //initial delay
                interval*60*1000);  //subsequent rate
    }
    
    public static void cancel() {
        timer.cancel();
        syncTimer = null;
    }
    
    class UpdateTask extends TimerTask {
        public void run() {
            try {
            	List<ResourceDiscoveryAdaptor> discoveryAdaptors = new ArrayList<ResourceDiscoveryAdaptor>();
                discoveryAdaptors.add(new GpirDiscoveryAdaptor());
                discoveryAdaptors.add(new IISDiscoveryAdaptor());
                
                for (ResourceDiscoveryAdaptor adaptor: discoveryAdaptors) {
                	SyncManager.sync(adaptor.retrieveResources());
                }
                
//                jSync = new JobSynchronizer();
                log.debug("Successfully synced with info service.");
            } catch (SynchronizationException se) {
                log.error("Failed to update database from gpir.",se);
                
            }
        } // run()
    } // class RemindTask
} // class AnnoyingBeep