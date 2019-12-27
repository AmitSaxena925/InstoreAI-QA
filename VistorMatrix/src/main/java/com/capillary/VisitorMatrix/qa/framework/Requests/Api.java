package com.capillary.VisitorMatrix.qa.framework.Requests;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Method;

import org.apache.commons.io.output.WriterOutputStream;

/**
 * Created by johnson_phillips on 2/1/18.
 */
public class Api extends RestAssured {

	private final String logFolder;
    private PrintStream printStream;
    private LogConfig originalLogConfig;
 
    public Api(String logFolder) {
        File dir = new File(logFolder);
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
        if (logFolder.endsWith("/")) {
            this.logFolder = logFolder.substring(0, logFolder.length() - 1);
        } else {
            this.logFolder = logFolder;
        }
    }
 
    @SuppressWarnings("deprecation")
	protected void starting(Method method) {
        originalLogConfig = RestAssured.config().getLogConfig();
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(logFolder + "/" + method.getName() + ".log");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        printStream = new PrintStream(new WriterOutputStream(fileWriter), true);
        RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().defaultStream(printStream).enablePrettyPrinting(false));
    }
 
 
    protected void finished(Method method) {
        if (printStream != null) {
            printStream.close();
        }
 
        if (originalLogConfig != null) {
            RestAssured.config = RestAssuredConfig.config().logConfig(originalLogConfig);
        }
    }
}
