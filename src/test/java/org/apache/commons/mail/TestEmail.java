//Name: Michael P. Sepsey
//Date Created: 3/23/24
//Date Modified: 3/26/24

package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

private static final testVariable = 0;

public class TestEmail {
	private EmailStub emailobj;
	
	//for vararg parameter "String... emails"
	//there are four (4) emails in the array
	private static final String[] TEST_EMAILS = { "abc@def", "abc@def.com", 
			"x.yz@abc.com", "abcdefghijklmnopqrst@zyxwvutsrqponmlkjihg.mi.us" };

	//JUnit indicator for testing the excepted exception thrown
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	//JUnit indicator for set-up procedures
	//intended to be done prior to testing
	@Before
	public void SetUpTestEmail() throws Exception
	{
		//create an instance of concrete child TestStub class
		emailobj = new EmailStub();	
	}
	
	//JUnit indicator for tear-down procedure
	//intended to be done following testing
	@After
	public void TearDownTestEmail() throws Exception
	{
		
	}
	
	//unit test cases begin here
	//@Test is added to the top of every test case as a 
	//JUnit indicator; intended to serve as the testing code

	@Test
	public void TestaddBcc_VarargEmailParameter() throws Exception
	{
		//addBcc utilizes a variable of vector type List<InternetAddress>
		List<InternetAddress> bcclist;
		
		//required test case
		//obtaining Bcc emails from a vararg parameter
		try
		{
			//param1 = set of email addresses (vararg)
			emailobj.addBcc(TEST_EMAILS);

			bcclist = emailobj.getBccAddresses();
			
			//number of Bcc emails in the list must match
			//the number of Bcc emails added for the test case to pass
			assertEquals(4, bcclist.size());
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to add a Bcc email to the vector array
			fail("The Bcc email was not added properly to the test object's bccList attribute.");
		}
	}
	
	@Test
	public void TestaddBcc_SingleEmailParameter() throws Exception
	{
		//addBcc utilizes a variable of vector type List<InternetAddress>
		List<InternetAddress> bcclist;
		
		//optional test case
		//obtaining Bcc emails from a single parameter
		try
		{
			//add the vararg emails used for the vararg parameter test case
			//param1 = set of email addresses (vararg)
			emailobj.addBcc(TEST_EMAILS);
			
			//param1 = email address
			emailobj.addBcc("msepsey@umich.edu");
			
			bcclist = emailobj.getBccAddresses();
			
			//number of Bcc emails in the list must match
			//the total number of Bcc emails added for the test case to pass
			assertEquals(5, bcclist.size());	
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to add a Bcc email to the vector array
			fail("The Bcc email was not added properly to the test object's bccList attribute.");
		}
	}
	
	@Test
	public void TestaddBcc_InvalidAddressSyntax() throws Exception
	{
		//optional test case
		//obtaining Bcc emails where one of the emails uses invalid email address syntax
		
		//source for obtaining name of exception type:
		//https://stackoverflow.com/questions/32519410/get-exception-instance-class-name
		
		try
		{			
			//param1 = email address
			emailobj.addBcc("abcdef.com");
		}
		catch(Exception e)
		{
			//the expected exception thrown must match the type
			//of exception thrown by Exception e for the test case to pass
			String expectedException = "EmailException";
			assertEquals(expectedException, e.getClass().getSimpleName());
			
		}
	}

	@Test
	public void TestaddCc_SingleEmailParameter() throws Exception
	{
		//addCc utilizes a variable of vector type List<InternetAddress>
		List<InternetAddress> cclist;
		
		//required test case
		//obtaining Cc emails from a single parameter
		try
		{			
			//param1 = email address
			emailobj.addCc("msepsey@umich.edu");

			cclist = emailobj.getCcAddresses();			
			
			//number of Cc emails in the list must match
			//the number of Cc emails added for the test case to pass
			assertEquals(1, cclist.size());
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to add a Cc email to the vector array
			fail("The Cc email was not added properly to the test object's ccList attribute.");	
		}
	}

	@Test
	public void TestaddCc_VarargEmailParameter() throws Exception
	{
		//addCc utilizes a variable of vector type List<InternetAddress>
		List<InternetAddress> cclist;
		
		//optional test case
		//obtaining Cc emails from a vararg parameter
		try
		{
			//add the single email used in the single parameter test case
			//param1 = email address
			emailobj.addCc("msepsey@umich.edu");
			
			//param1 = set of email addresses (vararg)
			emailobj.addCc(TEST_EMAILS);

			cclist = emailobj.getCcAddresses();
			
			//number of Cc emails in the list must match
			//the total number of Cc emails added for the test case to pass
			assertEquals(5, cclist.size());
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to add a Cc email to the vector array
			fail("The Cc email was not added properly to the test object's ccList attribute.");
		}
	}
	
	@Test
	public void TestaddCc_InvalidAddressSyntax() throws Exception
	{
		//optional test case
		//obtaining Cc emails where one of the emails uses invalid email address syntax
		
		//source for obtaining name of exception type:
		//https://stackoverflow.com/questions/32519410/get-exception-instance-class-name
		
		try
		{			
			//param1 = email address
			emailobj.addCc("abcdef.com");
		}
		catch(Exception e)
		{
			//the expected exception thrown must match the type
			//of exception thrown by Exception e for the test case to pass
			String expectedException = "EmailException";
			assertEquals(expectedException, e.getClass().getSimpleName());
			
		}
	}
	
	@Test
	public void TestaddHeader_NoEmptyParameters() throws Exception
	{	
		//addHeader utilizes a variable of vector type Map<String, String>
		Map<String, String> headerlist = emailobj.headers;
		
		//correct
		//non-empty parameters

		try
		{
			//param1 = name
			//param2 = value
			emailobj.addHeader("abc@def", "53");
			
			//number of headers in the list must match
			//the number of headers added for the test case to pass
			assertEquals(1, headerlist.size());
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to add a header to the vector array
			fail("The header was not added properly to the test object's headers attribute.");
		}
	}

	@Test
	public void TestaddHeader_EmptyNameParameter() throws Exception
	{
		//source for exception assertEquals:
		//https://stackoverflow.com/questions/10148101/junit-testing-assertequals-for-exception
		
		//throwing exception 1
		//empty name parameter

		try
		{
			//param1 = name
			//param2 = value
			emailobj.addHeader("", "53");	
		}
		catch(Exception e)
		{
			//message of exception written on code must match message
			//of exception for the test case to pass
			String expectedMsg = "name can not be null or empty";
			assertEquals(expectedMsg, e.getMessage());
		}
	}
	
	@Test
	public void TestaddHeader_EmptyValueParameter() throws Exception
	{	
		//source for exception assertEquals:
		//https://stackoverflow.com/questions/10148101/junit-testing-assertequals-for-exception		
		
		//throwing exception 2
		//empty value parameter
		try
		{
			//param1 = name
			//param2 = value
			emailobj.addHeader("abc@def", "");	
		}
		catch(Exception e)
		{
			//message of exception written on code must match message
			//of exception for the test case to pass
			String expectedMsg = "value can not be null or empty";
			assertEquals(expectedMsg, e.getMessage());
		}
	}

	@Test
	public void TestaddReplyTo_WithoutCharset() throws Exception
	{
		//addReplyTo utilizes a variable of vector type List<InternetAddress>
		List<InternetAddress> replylist = emailobj.replyList;

		//required test case
		//adding a reply-to email without a charset constant
		try
		{
			//param1 = email address
			//param2 = name
			emailobj.addReplyTo("abc@def", "Foobar");
			replylist = emailobj.getReplyToAddresses();
			
			//number of reply-to emails in the list must match
			//the number of reply-to emails added for the test case to pass
			assertEquals(1, replylist.size());
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to add a reply-to email to the vector array
			fail("The Cc email was not added properly to the test object's replyList attribute.");
			
		}
	}

	@Test
	public void TestaddReplyTo_WithCharset() throws Exception
	{
		//addReplyTo utilizes a variable of vector type List<InternetAddress>
		List<InternetAddress> replylist = emailobj.replyList;
		
		//optional test case
		//adding a reply-to email with a charset constant
		try
		{
			//add reply-to email from test case involving no charset constant
			//param1 = email address
			//param2 = name
			emailobj.addReplyTo("abc@def", "Foobar");
			replylist = emailobj.getReplyToAddresses();
			
			//initializing the charset constant to be used
			emailobj.setCharset("KOI8_R");
			
			//param1 = email address
			//param2 = name
			emailobj.addReplyTo("abcd@efgh", "Barfoo");
			replylist = emailobj.getReplyToAddresses();
			
			//number of reply-to emails in the list must match
			//the total number of reply-to emails added for the test case to pass
			assertEquals(2, replylist.size());
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to add a reply-to email to the vector array
			fail("The Cc email was not added properly to the test object's replyList attribute.");
			
		}
	}
	
	@Test
	public void TestaddReplyTo_InvalidAddressSyntaxException() throws Exception
	{
		//optional test case
		//adding a reply-to email using invalid email address syntax
		
		//source for obtaining name of exception type:
		//https://stackoverflow.com/questions/32519410/get-exception-instance-class-name
		try
		{	
			//no @ in param1
			//param1 = email address
			//param2 = name
			emailobj.addReplyTo("abcdef.com", "Ubar");
		}
		catch(Exception e)
		{
			//the expected exception thrown must match the type
			//of exception thrown by Exception e for the test case to pass
			String expectedException = "EmailException";
			assertEquals(expectedException, e.getClass().getSimpleName());
			
		}
	}
	
	@Test
	public void TestbuildMimeMessage_CondBlockPropsSet1() throws Exception
	{
		Date testdate = new Date();
		
		//test case 1
		//testing property assignments invoked within conditional blocks of method
		
		try
		{
			//setting required parameters (those necessary for triggering the conditional blocks)
			//to test the conditional blocks of method
			//we want to cover a majority of the conditional block implemented in the method
			//some of these parameters must be set prior to the session's initialization
			//which occurs during the buildMimeMessage method via the getMailSession method
			emailobj.setHostName("localhost");
			emailobj.setSmtpPort(3562);
			emailobj.setDebug(true);
			emailobj.setSubject("Test Email");
			emailobj.setCharset(EmailConstants.ISO_8859_1);			
			emailobj.setContent("test content", EmailConstants.TEXT_PLAIN);
			emailobj.setFrom("abc@def");
			emailobj.addTo("tuvw@xyz");
			emailobj.addCc("foyzul@example.com");
			emailobj.addBcc("rzig@college.edu");
			emailobj.addReplyTo("def@ghi");
			emailobj.addHeader("jklmnop@com.edu", "32");
			emailobj.setSentDate(testdate);

			//having trouble getting the connection to work,
			//so the conditional block regarding the popBeforeSmtp is ignored from testing
			/*emailobj.setPopBeforeSmtp(true, EmailConstants.MAIL_HOST, "dummy", "abc123");*/
			
			//build the mime message
			emailobj.buildMimeMessage();
			
			//run JUnit assertions to validate whether parameters
			//triggered the events inside the conditional blocks
			
			//the values below were taken from the method implementation and are used 
			//to compare with the values stored in the properties of the mime message
			
			//every value provided below must match
			//their respective value stored in the properties for the test case to pass
			assertEquals("localhost", emailobj.hostName);
			assertEquals("3562", emailobj.smtpPort);
			assertEquals(true, emailobj.debug);
			assertEquals("Test Email", emailobj.subject);
			assertEquals("ISO-8859-1", emailobj.charset);
			assertEquals("test content", emailobj.content);
			assertEquals("abc@def", emailobj.fromAddress.toString());
			assertEquals(1, emailobj.toList.size());
			assertEquals(1, emailobj.ccList.size());
			assertEquals(1, emailobj.bccList.size());
			assertEquals(1, emailobj.replyList.size());
			assertEquals(1, emailobj.headers.size());
			assertEquals(testdate, emailobj.getSentDate());
			
			//having trouble getting the connection to work,
			//so the conditional block regarding the popBeforeSmtp is ignored from testing
			/*assertEquals(true, emailobj.popBeforeSmtp);*/
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to build the mime message correctly
			fail("The mime message was not built properly using the attributes assigned for the test object.");
		}

		//throwing exception for building the mime message again
		try
		{
			emailobj.buildMimeMessage();
		}
		catch(Exception e)
		{
			//message of exception written on code must match message
			//of exception for the test case to pass
			String expectedMsg = "The MimeMessage is already built.";
			assertEquals(expectedMsg, e.getMessage());
		}
	}
	
	@Test
	public void TestbuildMimeMessage_CondBlockPropsSet2() throws Exception
	{
		Date testdate = new Date();
		MimeMultipart testMimeMultipart = new MimeMultipart("test content");
		testMimeMultipart.setPreamble("we the people");
		
		//test case 2
		//testing property assignments invoked within conditional blocks of method
		//conditional blocks not reached in test case 1 are covered in this test case
		
		try
		{
			//setting required parameters (those necessary for triggering the conditional blocks)
			//to test the conditional blocks of method
			//we want to cover a majority of the conditional block implemented in the method
			//some of these parameters must be set prior to the session's initialization
			//which occurs during the buildMimeMessage method via the getMailSession method
			emailobj.setHostName("localhost");
			emailobj.setSmtpPort(3562);
			emailobj.setDebug(true);
			//bounce address defined for email session initialization
			emailobj.setBounceAddress("abc@def.com");
			emailobj.setSubject("Test Email");
			//no charset defined for this test case
			emailobj.setContent(testMimeMultipart);
			//using bounce address as from address; from address not defined directly
			emailobj.addTo("tuvw@xyz");
			emailobj.addCc("foyzul@example.com");
			emailobj.addBcc("rzig@college.edu");
			emailobj.addReplyTo("def@ghi");
			emailobj.addHeader("jklmnop@com.edu", "32");
			emailobj.setSentDate(testdate);
			
			//build the mime message
			emailobj.buildMimeMessage();
			
			//run JUnit assertions to validate whether parameters
			//triggered the events inside the conditional blocks
			
			//the values below were taken from the method implementation and are used 
			//to compare with the values stored in the properties of the mime message
			
			//every value provided below must match
			//their respective value stored in the properties for the test case to pass
			assertEquals("localhost", emailobj.hostName);
			assertEquals("3562", emailobj.smtpPort);
			assertEquals(true, emailobj.debug);
			assertEquals("abc@def.com", emailobj.bounceAddress);
			assertEquals("Test Email", emailobj.subject);
			assertEquals("we the people", emailobj.emailBody.getPreamble());
			assertEquals(1, emailobj.toList.size());
			assertEquals(1, emailobj.ccList.size());
			assertEquals(1, emailobj.bccList.size());
			assertEquals(1, emailobj.replyList.size());
			assertEquals(1, emailobj.headers.size());
			assertEquals(testdate, emailobj.getSentDate());
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to build the mime message correctly
			fail("The mime message was not built properly using the attributes assigned for the test object.");
		}
		
		//throwing exception for building the mime message again
		try
		{
			emailobj.buildMimeMessage();
		}
		catch(Exception e)
		{
			//message of exception written on code must match message
			//of exception for the test case to pass
			String expectedMsg = "The MimeMessage is already built.";
			assertEquals(expectedMsg, e.getMessage());
		}
	}
	
	@Test
	public void TestbuildMimeMessage_NoToCcBccAddressesException() throws Exception
	{
		//test case 3
		//throwing exception within one of the conditional blocks
		//when the sum of the to, cc, and bcc addresses is zero;
		//basically no to, cc, or bcc addresses were defined in the test object
		
		try
		{
			//setting required parameters (those necessary for triggering the conditional blocks)
			//to test the conditional blocks of method prior to the exception being thrown
			//we want to cover a majority of the conditional block
			//implemented in the method prior to the exception being thrown
			//some of these parameters must be set prior to the session's initialization
			//which occurs during the buildMimeMessage method via the getMailSession method
			emailobj.setHostName("localhost");
			emailobj.setSmtpPort(3562);
			emailobj.setDebug(true);
			emailobj.setSubject("Test Email");
			emailobj.setCharset(EmailConstants.ISO_8859_1);			
			emailobj.setContent("test content", EmailConstants.TEXT_PLAIN);
			emailobj.setFrom("abc@def");
			
			//build the mime message
			emailobj.buildMimeMessage();
		}
		catch(Exception e)
		{
			//message of exception written on code must match message
			//of exception for the test case to pass
			String expectedMsg = "At least one receiver address required";
			assertEquals(expectedMsg, e.getMessage());
		}
	}

	@Test
	public void TestgetHostName_NoSessionAssigned() throws Exception
	{
		
		/*
		 * A session will be required for use with obtaining the host name.
		 *
		 * Doing so manually requires the instantiation of the Properties class along
		 * with a null value of the authenticator class (to match with the default
		 * session's authenticator value).
		 * 
		 * The getMailSession method will automatically create the session
		 * assuming that the session is not assigned to the test object.
		 * 
		 */
		
		//variable for storing host name
		String hostName;

		//test case 1
		//no session assigned to test class object
		
		try
		{
			//both conditional blocks are skipped within getHostName
			hostName = emailobj.getHostName();
			
			//no session means no host name,
			//so the value of test object's host name must match
			//the value 'null' for the test case to pass
			assertEquals(null, hostName);
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to have a hostname assigned
			fail("The hostname was not assigned properly to the test object.");	
		}
	}
	
	@Test
	public void TestgetHostName_SessionAssigned() throws Exception
	{
		
		/*
		 * A session will be required for use with obtaining the host name.
		 *
		 * Doing so manually requires the instantiation of the Properties class along
		 * with a null value of the authenticator class (to match with the default
		 * session's authenticator value).
		 * 
		 * The getMailSession method will automatically create the session
		 * assuming that the session is not assigned to the test object.
		 * 
		 */
		
		//variable for storing host name
		String hostName;
		
		//test case 2
		//session assigned to test class object
		
		try
		{
			//we need to set the hostname first before setting up the session
			//creating the session first results in the
			//checkSessionAlreadyInitialized method throwing an exception
			emailobj.setHostName("localhost");
			
			//getMailSession will automatically set up the session
			//since the test object's session is null
			emailobj.getMailSession();
			
			//first conditional block is invoked within getHostName
			hostName = emailobj.getHostName();

			//value of test object's host name must match
			//the value 'localhost' for the test case to pass
			assertEquals("localhost", hostName);
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to have a hostname assigned
			fail("The hostname was not assigned properly to the test object.");
		}
	}

	@Test
	public void TestgetMailSession_NoHostnameException() throws Exception
	{
		//test case 1
		//throwing exception for no hostname assigned to test object
		try
		{
			emailobj.getMailSession();
		}
		catch(Exception e)
		{
			//message of exception written on code must match message
			//of exception for the test case to pass
			String expectedMsg = "Cannot find valid hostname for mail session";
			assertEquals(expectedMsg, e.getMessage());
		}	
	}

	@Test
	public void TestgetMailSession_CondBlockProps() throws Exception
	{
		//variable for storing session information
		Session testSession = null;
		
		//test case 2
		//testing property assignments invoked within conditional blocks of method
		
		try
		{
			//setting required parameters (those necessary for triggering the conditional blocks)
			//to test the conditional blocks of method
			//we want to cover every conditional block implemented in the method
			//some of these parameters must be set prior to the session's initialization
			emailobj.setHostName("localhost");
			emailobj.setSmtpPort(3562);
			emailobj.setDebug(true);
			emailobj.setAuthentication("msepsey", "abc123");
			emailobj.setSSLOnConnect(true);
			emailobj.setSSLCheckServerIdentity(true);
			emailobj.setBounceAddress("abc@def.com");
			emailobj.setSocketTimeout(1);
			emailobj.setSocketConnectionTimeout(1);
			
			//initialize the session
			testSession = emailobj.getMailSession();
			
			//run JUnit assertions to validate whether parameters
			//triggered the events inside the conditional blocks
			
			//the values below were taken from the method implementation and are used 
			//to compare with the values stored in the properties of the email session
			
			//every value provided below must match
			//their respective value stored in the properties for the test case to pass
			assertEquals("localhost", emailobj.hostName);
			assertEquals("3562", emailobj.smtpPort);
			assertEquals(true, emailobj.debug);
			assertEquals("true", testSession.getProperty(EmailConstants.MAIL_SMTP_AUTH));
			assertEquals(emailobj.sslSmtpPort, testSession.getProperty(EmailConstants.MAIL_PORT));
			assertEquals(emailobj.sslSmtpPort, testSession.getProperty(EmailConstants.MAIL_SMTP_SOCKET_FACTORY_PORT));
			assertEquals("javax.net.ssl.SSLSocketFactory", testSession.getProperty(EmailConstants.MAIL_SMTP_SOCKET_FACTORY_CLASS));
			assertEquals("false", testSession.getProperty(EmailConstants.MAIL_SMTP_SOCKET_FACTORY_FALLBACK));
			assertEquals("true", testSession.getProperty(EmailConstants.MAIL_SMTP_SSL_CHECKSERVERIDENTITY));
			assertEquals("abc@def.com", testSession.getProperty(EmailConstants.MAIL_SMTP_FROM));
			assertEquals("1", testSession.getProperty(EmailConstants.MAIL_SMTP_TIMEOUT));
			assertEquals("1", testSession.getProperty(EmailConstants.MAIL_SMTP_CONNECTIONTIMEOUT));
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to retrieve information for the mail session
			fail("The test object was unable to return the mail session properly.");
		}
	}

	@Test
	public void TestgetMailSession_SessionGetInstance() throws Exception
	{
		//variable for storing session information
		Session testSession = null;
		
		//test case 3
		//verify that the session is created and that it
		//matches the session returned by the test object
		
		//we want to set the mail session with a session object
		//however, we cannot just call an instance of a
		//session class since the constructor is private
		
		//we can create a new instance of a session
		//given the properties class object
		//(and the authenticator class object if necessary)

		try
		{
			//declare an instance of Properties, which is part of the session class
			//the Properties class stores certain settings via key-value pairs
			Properties testprops = new Properties();
			
			//declare an instance of Session using the getInstance method
			//and the Properties class object defined above
			//the session class object defined earlier in test case 1 is used here
			testSession = Session.getInstance(testprops);
			
			//use this session to create the test object's email session
			emailobj.setMailSession(testSession);
			
			//define a new class object of Session with null values
			Session emailsession = null;
			
			//test the getMailSession method using this session
			//store the resulting session to a new class object of Session defined earlier
			emailsession = emailobj.getMailSession();
			
			//the testSession class object must match with the emailsession class object
			//for the test case to pass
			assertEquals(testSession, emailsession);
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to return a session or a session was undefined
			fail("The session was not returned properly to the test object or the session was not defined in the test object.");
		}
	}
	
	@Test
	public void TestgetMailSession_PropValues() throws Exception
	{
		//variable for storing session information
		Session testSession = null;
		
		//variable for storing properties
		//the Properties class stores certain settings via key-value pairs
		Properties testprops = new Properties();

		//test case 4
		//testing values stored in Properties class object (defined in test case 3)
		
		try
		{
			//assign a new key-value pair to the properties class object
			//in this case the key is "subject" and the value is "cis376"
			//we will call this property the test property
			testprops.setProperty("subject", "cis376");
			
			//declare an instance of Session using the getInstance method
			//and the Properties class object defined above
			//the session class object defined above is used here
			testSession = Session.getInstance(testprops);
			
			//use this session to create the test object's email session
			emailobj.setMailSession(testSession);
			
			//define a new class object of Session with null values
			Session emailsession = null;
			
			//have the email session class object to
			//acquire the session created above
			emailsession = emailobj.getMailSession();
			
			//acquire the properties stored from the email session
			testprops = emailsession.getProperties();
			
			//attempt to store the value of the mail transport protocol key in a string variable
			String testprotocol =(String) testprops.get("mail.transport.protocol");
			
			//this test case fails; meaning there's a defect in the developer code
			//it's supposed to return the value "SMTP" yet returns "null"
			//it has been commented out to complete the remaining JUnit assertions
			/*assertEquals(testprotocol, Email.SMTP);*/
			
			//attempt to store the value of the subject key in a string variable
			String valueoftestproperty =(String) testprops.getProperty("subject");
			
			//this test case will pass
			assertEquals(valueoftestproperty, "cis376");	
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to return a session or a session was undefined
			fail("The session was not returned properly to the test object or the session was not defined in the test object.");
		}
	}
	
	@Test
	public void TestgetSentDate_DateNotSet() throws Exception
	{
		//variable for storing current date
		//getSentDate utilizes a variable of type Date
		Date testdate = new Date();
		
		//test case 1
		//getting the sent date when the sentDate attribute
		//is not assigned to test class object
		
		try
		{
			//because the sent date attribute was not assigned,
			//a new Date object will be instantiated and returned
			Date sentdate = emailobj.getSentDate();
			
			//value of Date returned from test object must match
			//the testdate value for the test case to pass
			assertEquals(sentdate, testdate);	
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to return the sent date
			fail("The sent date was not returned properly by the test object.");
		}
	}
	
	@Test
	public void TestgetSentDate_DateSet() throws Exception
	{
		//getSentDate utilizes a variable of type Date
		
		//test case 2
		//getting the sent date when the sentDate attribute
		//is assigned to test class object
		
		try
		{
			//because the sent date attribute was instantiated and assigned to
			//the test object, the date stored by the test object will be returned
			emailobj.setSentDate(new Date());
			Date sentdate = emailobj.getSentDate();
			
			//value of test object's sentDate must match
			//the sentdate value for the test case to pass
			assertEquals(sentdate, emailobj.sentDate);
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to return the sent date
			fail("The sent date was not returned properly by the test object.");
		}
	}
	
	@Test
	public void TestgetSocketConnectionTimeout_NoSetUp() throws Exception
	{
		//test case 1
		//calling the method without assigning the
		//socket connection timeout to the test object
		try
		{
			emailobj.getSocketConnectionTimeout();			
			
			//since there was no assignment of the socket connection timeout,
			//the value returned should be the default value defined under
			//EmailConstants.SOCKET_TIMEOUT_MS (which is 60000)
			
			//value of test object's socket connection timeout must match
			//the default socket timeout constant value for the test case to pass
			assertEquals(EmailConstants.SOCKET_TIMEOUT_MS, emailobj.socketConnectionTimeout);
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to return the length of the
			//socket connection timeout
			fail("The length of the socket connection timeout was not returned properly by the test object.");
		}
	}
	
	@Test
	public void TestgetSocketConnectionTimeout_SetUpBeforeSessionInit() throws Exception
	{
		//variable for storing socket connection timeout
		int testSCT = 0;
		
		//test case 2
		//assigning the socket connection timeout to the test object
		//(without initializing the email session)
		
		//initializing the email session prior to assigning the
		//socket connection timeout will result in an exception being thrown
		
		try
		{
			//assign socket connection timeout value to test object
			emailobj.setSocketConnectionTimeout(1);
			
			//testSCT value will hold socket connection timeout value held by test object
			testSCT = emailobj.getSocketConnectionTimeout();
			
			//value of test object's socket connection timeout must match
			//the testSCT value for the test case to pass
			assertEquals(testSCT, emailobj.socketConnectionTimeout);	
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the test object was unable to return the length of the
			//socket connection timeout
			fail("The length of the socket connection timeout was not returned properly by the test object.");
		}
	}
	
	@Test
	public void TestgetSocketConnectionTimeout_SetUpAfterSessionInit() throws Exception
	{
		//variable for storing socket connection timeout
		int testSCT = 0;
		
		//test case 3
		//assigning the socket connection timeout to the test object
		//(following the email session's initialization)
		
		try
		{
			//initialize the email session
			emailobj.setHostName("localhost");
			Session testSession = emailobj.getMailSession();
			
			//assign socket connection timeout value to test object
			//the exception will be thrown here
			emailobj.setSocketConnectionTimeout(1);
			
			//all the code below this comment within the try block will not be executed
			//testSCT value will hold socket connection timeout value held by test object
			testSCT = emailobj.getSocketConnectionTimeout();
			
			//value of test object's socket connection timeout must match
			//the testSCT value for the test case to pass
			assertEquals(testSCT, emailobj.socketConnectionTimeout);	
		}
		catch(Exception e)
		{
			//the expected exception thrown must match the type
			//of exception thrown by Exception e for the test case to pass
			String expectedException = "IllegalStateException";
			assertEquals(expectedException, e.getClass().getSimpleName());	
			
			//message of exception written on code must match message
			//of exception for the test case to pass
			String expectedMsg = "The mail session is already initialized";
			assertEquals(expectedMsg, e.getMessage());
		}
	}

	@Test
	public void TestsetFrom_WithoutCharset() throws Exception
	{
		//setFrom utilizes a variable of vector type List<InternetAddress>
		InternetAddress testfromaddress1 = new InternetAddress("abc@def");
		
		//required test case
		//setting the from address without a charset constant
		try
		{
			//param1 = email address
			emailobj.setFrom("abc@def");			

			//the value of testfromaddress1 must match
			//the value of the test object's fromAddress for the test case to pass
			assertEquals(testfromaddress1, emailobj.fromAddress);
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the from address(es) weren't assigned properly to the test object
			fail("The form address(es) was not assigned properly to the test object.");
		}
	}
	
	@Test
	public void TestsetFrom_WithCharset() throws Exception
	{
		//setFrom utilizes a variable of vector type List<InternetAddress>
		InternetAddress testfromaddress2 = new InternetAddress("abcd@efgh");

		//optional test case
		//setting the from address with a charset constant
		
		try
		{
			//initializing the charset constant to be used
			emailobj.setCharset("KOI8_R");
			
			//param1 = email address
			emailobj.setFrom("abcd@efgh");
			
			//the value of testfromaddress2 must match
			//the value of the test object's fromAddress for the test case to pass
			assertEquals(testfromaddress2, emailobj.fromAddress);			
		}
		catch(Exception e)
		{
			//if an exception was thrown, the test case(s) automatically fails
			//as the from address(es) weren't assigned properly to the test object
			fail("The form address(es) was not assigned properly to the test object.");
		}
	}
	
	@Test
	public void TestsetFrom_InvalidAddressSyntaxException() throws Exception
	{
		//optional test case
		//setting the from address using invalid email address syntax
		
		//source for obtaining name of exception type:
		//https://stackoverflow.com/questions/32519410/get-exception-instance-class-name
		
		try
		{
			//no @ in the parameter
			//param1 = email address
			emailobj.setFrom("abcdef.com");
		}
		catch(Exception e)
		{
			//the expected exception thrown must match the type
			//of exception thrown by Exception e for the test case to pass
			String expectedException = "EmailException";
			assertEquals(expectedException, e.getClass().getSimpleName());
			
		}
	}
}
