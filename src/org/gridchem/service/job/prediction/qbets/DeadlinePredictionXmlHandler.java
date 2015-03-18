/* 
 * Created on May 22, 2008
 * 
 * Developed by: Rion Dooley - dooley [at] tacc [dot] utexas [dot] edu
 * 				 TACC, Texas Advanced Computing Center
 * 
 * https://www.tacc.utexas.edu/
 */

package org.gridchem.service.job.prediction.qbets;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
@SuppressWarnings("unused")
public class DeadlinePredictionXmlHandler extends AbstractXmlHandler {

    private Document doc;
    private Element elRoot;
    private Element elResponse;
    
    public DeadlinePredictionXmlHandler(String xml) {
        super(xml);
    }

    private void getDeadline() {
        if(strXml == null) {
            System.out.println("String XML was null, returning...");
            beans = null;
            return;
        }
        SAXBuilder builder = new SAXBuilder();
        StringReader xmlResultStream = new StringReader(strXml);
        try {
            doc = builder.build(xmlResultStream);
        } catch(JDOMException e) {
            //e.printStackTrace();
            //TODO: is this the right way to handle this?
            beans = null;
            return;
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        elRoot = doc.getRootElement();
        Response resource = new Response(new DeadlinePredictionFactory());
            
            
        if(elResponse != null) {
            resource.build(elResponse);
            beans.put(resource.getBean());
        }
    }

    public QbetsCollection getBeans() {
        if(beans == null) {
            beans = new ResponseList();
            
            getDeadline();
            
            return beans;
        } else {
            return beans;
        }
    }
}
