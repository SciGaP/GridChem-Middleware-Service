/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Mar 28, 2006
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

package org.gridchem.service.beans;

/**
 * Bean for usage information.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
 @SuppressWarnings("unused")
public class UsageBean {
    
	private double allocated = 0;
    private double used = 0;
	private double balance = 0;
    
    public UsageBean() {}
    
    public UsageBean(double allocated, double used) {
        this.allocated = allocated;
        this.used = used;
        this.balance = allocated - used;
    }
    
    public UsageBean(double allocated, double used, double balance) {
        this.allocated = allocated;
        this.used = used;
        this.balance = balance;
    }
    
    /**
     * @return Returns the allocated.
     */
    public double getAllocated() {
        return allocated;
    }
    /**
     * @param allocated The allocated to set.
     */
    public void setAllocated(double allocated) {
        this.allocated = allocated;
    }
    /**
     * @return Returns the balance.
     */
    public double getBalance() {
        return (this.allocated - this.used);
    }
    /**
     * @param balance The balance to set.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
    /**
     * @return Returns the used.
     */
    public double getUsed() {
        return used;
    }
    /**
     * @param used The used to set.
     */
    public void setUsed(double used) {
        this.used = used;
    }
    
    public String toString() {
        return getAllocated() + ":" + getUsed() + ":" + getBalance();
    }
}
