/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 30, 2005
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

package org.gridchem.service.util.gat;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.gridchem.service.model.LogicalFile;

/**
 * GMS utility class.
 * 
 * @author Rion Dooley < dooley [at] cct [dot] lsu [dot] edu >
 *
 */
public class Utility {
    
	public static String getHome() { 
    
        String home = null;
        
        try {
            home = Class.forName("org.gridchem.service.gms.util.Utility").getResource("Utility.class").getFile();
            
            //System.out.println("Application home is: " + home);
            
            home = home.replaceAll("%20"," ");
            
            if (home.indexOf("build") != -1) {
                home = home.substring(0, home.indexOf("build"));
            } else if (home.indexOf("lib") != -1) {
                home = home.substring(0, home.indexOf("lib"));
            } else if (home.indexOf("!") != -1) {
                home = home.substring(0,home.lastIndexOf("!"));
                System.out.println("jar path is: " + home);
                home = home.substring(0,home.lastIndexOf("/"));
                System.out.println("jar revised path is: " + home);
            } else {
                home = home.substring(0,home.lastIndexOf(File.separator));
            }
            
            if (!home.endsWith(File.separator)) {
                home = home + File.separator;
            }
            home = home.replace('/', File.separator.charAt(0));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return home;
    }
    
    public static String getNamesFromSet(Set<String> set) {
        int i = 0;
        String names = "";
        
        if(set == null)
            throw new IllegalArgumentException("Cannot parse null Set");
        
        for(i=0;i<set.size();i++) {
            //names.concat(set.get(i).getName());
            
        }
        
        return names;
    }
    
	/**
	 * Get the space-delimited list of file names in the set.
	 * @param gmsLogicalFile
	 * @return
	 */
	public static String getFileNames (Set<LogicalFile> gmsLogicalFile) {
	    int i;
	    String fileNames = "";
	    
	    for(i=0;i<gmsLogicalFile.size();i++) {
//	        fileNames.concat(gmsLogicalFile.get(i).getName() + " ");
	    }
	    
	    return fileNames;
	}
    

    /**
     * Returns name of current OS
     * @return
     */
    public final static String os() {
        String os = System.getProperty("os.name");

        if (os.startsWith("Windows")) {
            return "windows";
        }

        if ((os.equals("SunOS")) || (os.equals("Solaris"))) {
            return "solaris";
        }

        if (os.equals("Digital Unix")) {
            return "dec";
        }

        if (os.equals("Linux")) {
            return "linux";
        }

        if ((os.equals("Irix")) || (os.equals("IRIX"))) {
            return "irix";
        }

        if (os.equals("Mac OS X")) {
            return "osx";
        }

        return "Not Recognised";
    }
    
    public static String getFilePath(String fileName) throws ClassNotFoundException, IOException {
        URL jarname = Class.forName("org.gridchem.service.gms.util.Utility").getResource("Utility.class");
        String jarpath = jarname.getFile();
        if (Utility.os().startsWith("windows")){
            jarpath = jarpath.substring(0,jarpath.indexOf("!"));
            if (jarpath.indexOf("file") != -1)
                jarpath = jarpath.substring(5);
            else
                jarpath = jarpath.substring(0);
            
            jarpath = jarpath.replaceAll("%20"," ");
            
        }
        else {
            jarpath = jarpath.substring(jarpath.indexOf("/"),jarpath.indexOf("!"));
            jarpath = jarpath.replaceAll("%20"," ");
        }

        System.out.println("Jarpath = " + jarpath);
//        JarFile gridchemJarFile = new JarFile(jarpath);
//        JarEntry hibernateFile = gridchemJarFile.getJarEntry(fileName);
        File jarFile = new File(jarpath);
        
        if (jarFile != null) {
            System.out.println("Returning " + jarFile.getParentFile().getParentFile()+File.separator+fileName);
            return jarFile.getParentFile().getParentFile()+File.separator+fileName;
        }
        return null;
    }

}
