/* 
 * Created on Feb 5, 2008
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.gridchem.service.session;

import java.util.Random;

import org.gridchem.service.dao.SessionDao;


public class SessionKeyGenerator {

    private static final Random random = new Random();
    
    public static String generateKey() {
    
        String key = "";
        
        do {
            key = (
                (System.currentTimeMillis() * 100) +
                "." + (100 + (Math.abs(random.nextInt()) % 1000)));
                   
        } while (!SessionDao.isUnique(key));   
            
        return key;
            
    }
}
