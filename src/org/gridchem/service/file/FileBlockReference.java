/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on Dec 6, 2006
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

package org.gridchem.service.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.gridchem.service.exceptions.FileManagementException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Class to hold a reference to a file on local disk that we're
 * sending to the client one block at a time.  This class holds
 * open an BufferedInputStream and reads the next BLOCK_SIZE chunk
 * of data when the next() method is called.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class FileBlockReference {
    public static final int BLOCK_SIZE = 131072;
    private String fileName;
    private int index;
    
    FileInputStream fis;
    BufferedInputStream bis;
   
    
    public FileBlockReference(String fileName, int index) {
        this.fileName = fileName;
        
        try {
            
//            fis = new FileInputStream(fileName);
//            isr = new InputStreamReader(fis,
//                                      "UTF8");
//            in = new BufferedReader(isr);
            
            fis = new FileInputStream(fileName);
            
            bis = new BufferedInputStream(fis,BLOCK_SIZE);
            
        } catch (FileNotFoundException e) {
            
            throw new FileManagementException("Could not find file on server.",e);
            
        } 
        
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * Read the current block of data and return it as a string.
     * 
     * @return
     * @throws FileManagementException
     */
    public String next() throws FileManagementException {
        
        try {
//            StringBuffer buffer = new StringBuffer();
//            
//            int ch;
//            int readCount = 0;
//            while ((ch = in.read()) > -1 &&
//                    readCount < BLOCK_SIZE) {
//                buffer.append((char)ch);
//                readCount++;
//            }
//            
//            index++;
//            
//            return buffer.toString();
            
            byte[] data;
            
            if (bis.available() < BLOCK_SIZE) {
                data = new byte[bis.available()];
            } else {
                data = new byte[BLOCK_SIZE];
            }
            
            bis.read(data);
            
            index++;
            
            return Base64.encode(data);
            
        } catch (Exception e) {
            throw new FileManagementException("Could not read block " + index,e);
        }
        
    }
    
    /**
     * Closes the buffered input and file input readers used to read the
     * file represened by this FBR.
     * 
     * @throws IOException
     */
    public void close() throws IOException {
        try {
            bis.close();
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
}
