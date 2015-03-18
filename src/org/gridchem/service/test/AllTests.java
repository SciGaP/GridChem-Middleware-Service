package org.gridchem.service.test;


import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.gridchem.service.test.dao.ccg.*;
import org.gridchem.service.test.managers.*;
import org.gridchem.service.test.service.*;

/**
 * Run all unit tests for GridChem middlewae service.
 *
 * @author Rion Dooley <dooley [at] cct [dot] lsu [dot] edu>
 */
public class AllTests {

	public static Test suite() {

		TestSuite suite = new TestSuite();
		
//		suite.addTest(new TestSuite(GMSTestCase.class));  // passed
		
		/**
		 *  Persistence Layer Tests
		 */

//		suite.addTest(new TestSuite(SessionDaoTest.class));  // passed new
//		suite.addTest(new TestSuite(UserDaoTest.class)); // passed new 
		suite.addTest(new TestSuite(ProjectDaoTest.class)); // passed new
//		suite.addTest(new TestSuite(ResourceDaoTest.class)); // passed new
//		suite.addTest(new TestSuite(SoftwareDaoTest.class)); // passed new
//		suite.addTest(new TestSuite(BlackListDaoTest.class)); // passed new
//		suite.addTest(new TestSuite(JobDaoTest.class)); // passed new
//		suite.addTest(new TestSuite(WorkflowDaoTest.class)); // passed new
//		suite.addTest(new TestSuite(NotificationDaoTest.class)); // passed new
//		suite.addTest(new TestSuite(LogicalFileDaoTest.class)); // passed new
		
		/**
		 *  Business Layer Tests
		 */
//		suite.addTest(new TestSuite(CCGLoginProviderTest.class)); // passed new
//		suite.addTest(new TestSuite(TeraGridLoginProviderTest.class)); // passed new
//		suite.addTest(new TestSuite(SessionManagerTest.class)); // passed new
//		suite.addTest(new TestSuite(NotificationManagerTest.class)); // passed new
//		suite.addTest(new TestSuite(JobManagerTest.class)); // passed new
//		suite.addTest(new TestSuite(UserManagerTest.class)); // passed new
//		suite.addTest(new TestSuite(ProjectManagerTest.class)); // passed new
//		suite.addTest(new TestSuite(FileManagerTest.class)); // passed new
		
		/**
		 *  Adaptor Layer Tests
		 */
		
//		suite.addTest(new TestSuite(TeraGridProfileServiceClientTest.class)); //
		
		/**
		 *  Synchronization Tests
		 */
		
//      suite.addTest( SynchManagerTest.suite() ); // passed - ranch broken in iis
		
		/**
		 *  Servlet Interface Tests
		 */
		
//      suite.addTest(new TestSuite(UserServiceTest.class)); // passed new
//		suite.addTest(new TestSuite(SessionServiceTest.class)); // passed new
//		suite.addTest(new TestSuite(ProjectServiceTest.class));  // passed new
//      suite.addTest(new TestSuite(ResourceServiceTest.class)); // passed new
//		suite.addTest(new TestSuite(SoftwareServiceTest.class)); // passed new
//		suite.addTest(new TestSuite(NotificationServiceTest.class)); // passed new
//      suite.addTest(new TestSuite(JobServiceTest.class)); // ok passed new 
//		suite.addTest(new TestSuite(FileServiceTest.class));
		
		
//        suite.addTest( UserTest.suite() );
//        suite.addTest( JobCheckStatusTest.suite() );
//        suite.addTest( FileTest.suite() );
//        suite.addTest( NotificationTest.suite() );
//		suite.addTest( JobTest.suite() );
//        suite.addTest( JobSearchTest.suite() );
//      suite.addTest( LimingJobSearchTest.suite() );        
        // Administration level tests
//        suite.addTest( BatchTest.suite() );
//        suite.addTest( ExhaustiveJobSubmissionTest.suite() );
//      suite.addTest( ResourceTest.suite() );
        
      // Servlet interface tests

      
        // Infrastructure tests

        
        
		return suite;
	}

	public static void main(String args[]) {
		TestRunner.run( suite() );
	}
}
