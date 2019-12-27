package com.capillary.VisitorMatrix.qa.framework.Requests;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;

public class RequestResponseLogger {
	public static RequestResponseLogger apilogger;
	public StringWriter requestWriter;
	public PrintStream requestCapture;
	public StringWriter responseWriter;
	public PrintStream responseCapture;
	
	@SuppressWarnings("deprecation")
	public RequestResponseLogger()
	{
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter),true);
		responseWriter = new StringWriter();
		responseCapture = new PrintStream(new WriterOutputStream(requestWriter),true);
	}
	
	public static RequestResponseLogger getInstance()
	{
		if(apilogger == null)
		{
			apilogger = new RequestResponseLogger();
		}
		return apilogger;
	}
	
	public static void clearInstance()
	{
		apilogger = null;
	}

}
