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
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;


/**
 * Insert Template description here.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
@SuppressWarnings("unchecked")
public class MachineXmlHandler extends AbstractXmlHandler {

    private Document doc;
    private Element elRoot;
    private Namespace nsRoot;
    private List elResponseList;
    private Element elResponse;
    
    public MachineXmlHandler(String xml) {
        super(xml);
    }
    
    private void iterate() {
        if(strXml == null) {
            System.out.println("String XML was null, returning...");
            beans = null;
            return;
        }
        SAXBuilder builder = new SAXBuilder();
        StringReader xmlResultStream = new StringReader(strXml);
        try {
            doc = builder.build(xmlResultStream);
        }catch(JDOMException e) {
            // is this the right way to handle this?
            beans = null;
            return;
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        elRoot = doc.getRootElement();
        nsRoot = elRoot.getNamespace();
        elResponseList = elRoot.getChildren("machine", nsRoot);
        Iterator crIter = elResponseList.iterator();
        
        while (crIter.hasNext()) {
            elResponse = (Element) crIter.next();
            Response resource = new Response(new MachineFactory());
            
            
            if(elResponse != null) {
                resource.build(elResponse);
                beans.put(resource.getBean());
            }
        }
    }

    /* (non-Javadoc)
     * @see org.gridchem.service.gms.synch.qbets.XmlHandler#getBeans()
     */
    public QbetsCollection getBeans() {
        if(beans == null) {
            beans = new ResponseList();
            iterate();
            return beans;
        } else {
            return beans;
        }
    }

}
