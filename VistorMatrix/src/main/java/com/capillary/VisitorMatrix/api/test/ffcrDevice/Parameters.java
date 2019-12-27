package com.capillary.VisitorMatrix.api.test.ffcrDevice;

import java.util.HashMap;
import java.util.Map;

public class Parameters {

	public Map<String, String> queryParams;
	public Map<String, String> pathParams;
	public Map<String, String> headers;
	public static Parameters p;

	public static synchronized Parameters getParameterObj() {

		if (p == null) {
			p = new Parameters();
			return p;
		}
		return p;
	}

	public Parameters() {
		queryParams = new HashMap<String, String>();
		pathParams = new HashMap<String, String>();
		headers = new HashMap<String, String>();
	}

	public void setqueryParams(String keys, String values) {
		queryParams.put(keys, values);
	}

	public void setpathparams(String keys, String values) {
		pathParams.put(keys, values);
	}

	public void setheaderParams(String keys, String values) {
		headers.put(keys, values);
	}

	public void deleteparams() {
		queryParams.clear();
		pathParams.clear();
		headers.clear();
	}

	public void deleteparams(String Type, String key) {
		if (Type.equals("pathParams")) {
			pathParams.remove(key);
		} else if (Type.equals("queryParams")) {
			queryParams.remove(key);
		} else {
			headers.remove(key);
		}
	}

}
