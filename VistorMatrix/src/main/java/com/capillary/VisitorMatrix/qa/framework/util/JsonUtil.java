package com.capillary.VisitorMatrix.qa.framework.util;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	@SuppressWarnings("unchecked")
	public static String appendToJsonArray(JSONObject ResponsejsonObj, JSONObject toBeAppended,String updatekey/*, String removekey*/)
	{
		JSONObject jsonobj = ResponsejsonObj;
		JSONArray finaljsonarray = null;
		String[] keys = updatekey.split("\\.");
		for(int i =0 ; i<keys.length;i++)
		{
			if((ResponsejsonObj.get(keys[i]) instanceof JSONObject))
			{
				ResponsejsonObj = (JSONObject) ResponsejsonObj.get(keys[i]);				
			}
			else if (i==keys.length-1)
			{
				finaljsonarray = (JSONArray) ResponsejsonObj.get(keys[i]);
				finaljsonarray.add(toBeAppended);
				System.out.println("The updated json object is "+jsonobj.toString());
			}
			else
			{
				finaljsonarray = (JSONArray) ResponsejsonObj.get(keys[i]);
				ResponsejsonObj = (JSONObject) finaljsonarray.get(0);
			}			
		}
		System.out.println("The json is "+jsonobj.toString());
        return jsonobj.toString();        
    }
	
	@SuppressWarnings("unchecked")
	public static String appendJsonArrayToJsonArray(JSONObject ResponsejsonObj, JSONArray toBeAppended,String updatekey/*, String removekey*/)
	{
		JSONObject jsonobj = ResponsejsonObj;
		JSONArray finaljsonarray = null;
		String[] keys = updatekey.split("\\.");
		for(int i =0 ; i<keys.length;i++)
		{
			if((ResponsejsonObj.get(keys[i]) instanceof JSONObject))
			{
				ResponsejsonObj = (JSONObject) ResponsejsonObj.get(keys[i]);				
			}
			else if (i==keys.length-1)
			{
				finaljsonarray = (JSONArray) ResponsejsonObj.get(keys[i]);
				for(int j= 0 ; j< toBeAppended.size();j++)
				{
						JSONObject obj = (JSONObject) toBeAppended.get(j);
						finaljsonarray.add(obj);
				}
				System.out.println("The json array is "+finaljsonarray.toString());
				ResponsejsonObj.put(keys[i], finaljsonarray);
			}
			else
			{
				finaljsonarray = (JSONArray) ResponsejsonObj.get(keys[i]);
				ResponsejsonObj = (JSONObject) finaljsonarray.get(0);
			}			
		}
		System.out.println("The json is "+jsonobj.toString());
        return jsonobj.toString();        
    }
	
	public static JSONObject removeJsonObjectKey (JSONObject ResponsejsonObj, String[] keys)
	{	
		JSONObject jsonobj = ResponsejsonObj;
		for(String key : keys)
		{
			ResponsejsonObj = jsonobj;
			String[] removeKeys = key.split("\\.");
			for(int i =0 ; i<removeKeys.length ; i++)
			{
				if(ResponsejsonObj.containsKey(removeKeys[i]) == true)
				{
					if(i==removeKeys.length-1)
					{
						ResponsejsonObj.remove(removeKeys[i]);
					}
					else
					{
						ResponsejsonObj = (JSONObject) ResponsejsonObj.get(removeKeys[i]);
					}
				}
				else
				{
					System.out.println("Element not found");
				}
			}
		}
		return jsonobj;		
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject updateJsonObjectKey(JSONObject ResponsejsonObj, String updateKey, String value)
	{
		ResponsejsonObj.put(updateKey, value);
		return ResponsejsonObj;
	}
	
	public static JSONObject StringtoJson(String JsonString) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject jsonobject = (JSONObject) parser.parse(JsonString);
		return jsonobject;
	}
	
	public static boolean CompareJsonArray(String arr1, String arr2) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONArray APIJsonArray = (JSONArray)parser.parse(arr1);
		JSONArray DBJsonArray = (JSONArray) parser.parse(arr2);
//		Reporter.log("The API response JSON array is"+APIJsonArray.toJSONString());
//		Reporter.log("The DB response JSON array is"+DBJsonArray.toJSONString());
		System.out.println("The API response JSON array is"+APIJsonArray.toJSONString());
		System.out.println("The DB response JSON array is"+DBJsonArray.toJSONString());
		if(APIJsonArray.equals(DBJsonArray))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	public static String convertListMapToString(List<Map<String,Object>> lmo) throws JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String str = objectMapper.writeValueAsString(lmo);
		return str;
	}
	
	public static JSONArray convertListMaptoJsonArray(List<Map<String,Object>> lmo) throws ParseException, JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String str = objectMapper.writeValueAsString(lmo);
		JSONParser js = new JSONParser();
		JSONArray array = (JSONArray)js.parse(str);
		return array;
	}
	public static JSONObject convertMaptoJsonObject(Map<String,Object> map) throws JsonProcessingException, ParseException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		String str = objectMapper.writeValueAsString(map);
		JSONParser js = new JSONParser();
		JSONObject obj = (JSONObject)  js.parse(str);
		return obj;
	}
	public static JSONArray stringtoJsonArray(String str) throws ParseException
	{
		JSONParser js = new JSONParser();
		JSONArray array = (JSONArray) js.parse(str);
		return array;
	}
	

}
