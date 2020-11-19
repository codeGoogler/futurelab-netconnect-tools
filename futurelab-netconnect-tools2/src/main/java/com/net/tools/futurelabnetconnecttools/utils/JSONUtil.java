//package com.net.tools.futurelabnetconnecttools.utils;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JsonConfig;
//import org.apache.poi.hssf.record.formula.functions.T;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//public class JSONUtil {
//	/**
//	 * 数组转JSON
//	 * @param object
//	 * @return
//	 */
//	public static String arrayToJSON(Object object) {
//		Gson gson = new Gson();
//		return gson.toJson(object).toString();
//	}
//	/**
//	 * 将对象转换为json字符串对象
//	 *
//	 * @param object
//	 * @Description:
//	 */
//	public static  String objectToJSON(Object object) {
//		return new Gson().toJson(object);
//	}
//
//	public static String objectToJSONWithNull(Object object){
//		Gson gson = new GsonBuilder().serializeNulls().create();
//		return gson.toJson(object);
//	}
//	/**
//	 * json转对象
//	 * @param json
//	 * @param cla
//	 * @return
//	 */
//	public static <T> Object jsonToObject(String json,Class<T> cla){
//		return new Gson().fromJson(json, cla);
//	}
//	/**
//	 * json转list
//	 * @param json
//	 * @return
//	 */
//	public static <T> List<T> jsonToList(String json) {
//		Gson gson = new Gson();
//		return gson.fromJson(json, new TypeToken<T>() {
//		}.getType());
//	}
//
//	/**
//	 * list转list
//	 * @param list
//	 * @return
//	 */
//	public static <C,T>  List<C> listGsonToList(List<T> list,Class<C> cla) {
//		List<C> resultList = new ArrayList<C>();
//		try {
//			resultList = JSONArray.toList(JSONArray.fromObject(list),cla.newInstance(),new JsonConfig());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resultList;
//	}
//
//	/**
//	 * model转model
//	 * @param t
//	 * @return
//	 */
//	public static <C,T>  C modelGsonToModel(T t, Class<C> cls) {
//		try {
//			return (C) JSONObject.toBean(JSONObject.fromObject(t),cls);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * json转list
//	 * @param json
//	 * @return
//	 */
//	public static <T> List<T> jsonToList(String json,Class<T> cla) {
//		List<T> list = new ArrayList<T>();
//		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
//		for(final JsonElement elem : array){
//		list.add(new Gson().fromJson(elem, cla));
//		}
//
//		return list;
//
//	}
//
//	/**
//	 * json数据转map
//	 * @param json 数据
//	 * @return map格式
//	 */
//	public static Map<String, Object> jsonToMap(String json) {
//		Gson gson = new Gson();
//		return gson.fromJson(json, new TypeToken<Map<	String, Object>>() {
//		}.getType());
//	}
//
//	/**
//	 *  Jackson library
//	 * @param jsonInString
//	 * @return
//	 */
//	public final static boolean isJSONValid2(String jsonInString ) {
//		try {
//			final ObjectMapper mapper = new ObjectMapper();
//			mapper.readTree(jsonInString);
//			return true;
//		} catch (IOException e) {
//			return false;
//		}
//	}
//
//	/**
//	 * Google Gson
//	 * @param jsonInString
//	 * @return
//	 */
//	public final static boolean isJSONValid3(String jsonInString) {
//		try {
//			Gson gson = new Gson();
//			gson.fromJson(jsonInString, Object.class);
//			return true;
//		} catch(JsonSyntaxException ex) {
//			return false;
//		}
//	}
//
//
//	/**
//	 * @param filter
//	 * @param json
//	 * @return
//	 */
//	public  static boolean getFilerBoolean(String json,String filter) {
//		try {
//			JSONObject jsonObject = JSONObject.fromObject(json);
//			return jsonObject.optBoolean(filter);
//		} catch(JsonSyntaxException ex) {
//			return false;
//		}
//	}
//	public  static int  getFilerInt(String json,String filter) {
//		try {
//			JSONObject jsonObject = JSONObject.fromObject(json);
//			return jsonObject.optInt(filter);
//		} catch(JsonSyntaxException ex) {
//			return -1;
//		}
//	}
//
//	/**
//	 * @param filter
//	 * @param json
//	 * @return
//	 */
//	public  static String getFilerStr(String json,String filter) {
//		try {
//			JSONObject jsonObject = JSONObject.fromObject(json);
//			return jsonObject.optString(filter);
//		} catch(JsonSyntaxException ex) {
//			return "";
//		}
//	}
//	public  static JSONObject getFilterObj(String json,String filter) {
//		try {
//			JSONObject jsonObject = JSONObject.fromObject(json);
//			return jsonObject.optJSONObject(filter);
//		} catch(JsonSyntaxException ex) {
//			return null;
//		}
//	}
//	public  static Object  getFilterObj(JSONObject json, Class<T> tCalss, String filter) {
//		if(tCalss == null){
//			return  null;
//		}
//		try {
//			if(tCalss.isInstance(String.class)){
//				return json.optString(filter);
//			}else if(tCalss.isInstance(Integer.class)){
//				return json.optInt(filter);
//			}else if(tCalss.isInstance(double.class)){
//				return json.optDouble(filter);
//			}else if(tCalss.isInstance(boolean.class)){
//				return json.optBoolean(filter);
//			}else if(tCalss.isInstance(JSONObject.class)){
//				return json.optJSONObject(filter);
//			}else if(tCalss.isInstance(JSONArray.class)){
//				return json.optJSONArray(filter);
//			}
//			return json.optString(filter);
//		} catch(JsonSyntaxException ex) {
//			return null;
//		}
//	}
//	public static void main(String[] args) {
//
////		Map<String, Object> map = new HashMap<String, Object>();
////		 map.put("1", 11);
////		 map.put("2", "2");
////		 Gson gson = new Gson();
////		 map = jsonToMap(gson.toJson(map));
////		 System.out.println(gson.toJson(map));
//		 System.out.println(getFilterByZip("adgfafdgaz我加你.zip"));
//
//	}
//
//	public static  String getFilterByZip(String fileName){
//		String result = null;
//		if(!StringUtils.isEmpty(fileName)){
//			if(fileName.endsWith(".zip")){
//				result = fileName.substring(0,fileName.length()-4);
//			}else {
//				result = fileName;
//			}
//		}
//		return  result;
//	}
//
//	public static JSONObject getJSONObject(String key ,String value){
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put(key,value);
//		return  jsonObject;
//	}
//	public static JSONObject getJSONObject(String key ,int value){
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put(key,value);
//		return  jsonObject;
//	}
//}
