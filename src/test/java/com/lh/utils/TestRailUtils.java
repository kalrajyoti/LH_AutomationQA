package com.lh.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
//import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.lh.helper.Constants;
import com.lh.testConfig.TestProperty;

public class TestRailUtils {

	public static final int TEST_CASE_PASSED_STATUS = 1;
	public static final int TEST_CASE_FAILED_STATUS = 5;
	//log	private static Logger log = Logger.getLogger(TestRailUtils.class);


	private static Properties readInProperties() throws IOException {
		FileInputStream in = null;
		Properties prop = new Properties();
		try {
			in = new FileInputStream(new File(System.getProperty("user.dir")+
					TestProperty.TEST_RUN_ID));
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return prop;
	}

	private static String inputStreamToString(InputStream is)
			throws IOException {
		String line = "";
		StringBuilder total = new StringBuilder();

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		// Read response until the end
		while ((line = rd.readLine()) != null) {
			total.append(line);
		}

		// Return full string
		return total.toString();
	}


	
	public static void addResultForTestCase(String testCaseId, int status,
			String error) throws IOException, APIException {

		String testRunId = TestProperty.TEST_RUN_ID;

		APIClient client = new APIClient(Constants.RAILS_ENGINE_URL);
		client.setUser(TestProperty.TESTRAIL_USERNAME);
		client.setPassword(TestProperty.TESTRAIL_PASSWORD);
		Map data = new HashMap();
		data.put("status_id", status);
		data.put("comment", "Test Passed Status is Updated from Automation");
		JSONObject c = (JSONObject) client.sendPost("add_result_for_case/"+testRunId+"/"+testCaseId+"",data );

	}




	public static String getTestCaseId(String qualifiedName) {
		String id = "";
		Document doc = null;
		try {
			doc = getTestCasesDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (doc != null) {
			try {
				XPath xPath = XPathFactory.newInstance().newXPath();
				XPathExpression xPathExpression = xPath
						.compile("/testcases/testcase[title='" + qualifiedName
								+ "']/id");
				id = (String) (xPathExpression.evaluate(doc,
						XPathConstants.STRING));
			} catch (XPathExpressionException e) {
//log				log.error(e);
				//System.out.println(e);
			}
		}
		return id.trim();
	}

	private static Document getTestCasesDocument()
			throws ParserConfigurationException, SAXException, IOException {
		String pathToFile = Constants.RAILS_TESTCASES;
		return readXMLFile(pathToFile);
	}

	private static Document readXMLFile(String xml)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

		// InputSource is = new InputSource(new StringReader(xml));
		Document doc = docBuilder.parse(new File(System.getProperty("user.dir")+xml));

		// normalize text representation
		doc.getDocumentElement().normalize();
		return doc;
	}


}
