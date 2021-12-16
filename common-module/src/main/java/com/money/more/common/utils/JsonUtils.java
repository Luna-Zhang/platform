package com.money.more.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtils {
	private static final SerializeConfig config;
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		config = new SerializeConfig();
		config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
		config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
	}

	private static final SerializerFeature[] features = {
			SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
	};


	public static String toJSONString(Object object) {
		return JSON.toJSONString(object, config, features);
	}

	public static String toJSONNoFeatures(Object object) {
		return JSON.toJSONString(object, config);
	}

	public static Object toBean(String text) {
		return JSON.parse(text);
	}

	public static <T> T toBean(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}

	// 转换为数组
	public static <T> Object[] toArray(String text) {
		return toArray(text, null);
	}

	// 转换为数组
	public static <T> Object[] toArray(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz).toArray();
	}

	// 转换为List
	public static <T> List<T> toList(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}

	/**
	 * 将javabean转化为序列化的json字符串
	 * @param keyvalue
	 * @return
	 */
	public static Object beanToJson(KeyValue keyvalue) {
		String textJson = JSON.toJSONString(keyvalue);
		Object objectJson  = JSON.parse(textJson);
		return objectJson;
	}

	/**
	 * 将string转化为序列化的json字符串
	 * @param text
	 * @return
	 */
	public static Object textToJson(String text) {
		Object objectJson  = JSON.parse(text);
		return objectJson;
	}

	/**
	 * json字符串转化为map
	 * @param s
	 * @return
	 */
	public static Map stringToCollect(String s) {
		Map m = JSONObject.parseObject(s);
		return m;
	}

	/**
	 * 将map转化为string
	 * @param m
	 * @return
	 */
	public static String collectToString(Map m) {
		String s = JSONObject.toJSONString(m);
		return s;
	}


	/**
	 * @Title: obj2Json
	 * @Description: 将对象转换为JSON字符串
	 * @param obj   //对象模型
	 * @return String   //转换完毕的字符串
	 */
	public static String obj2Json(Object obj) {
		String str = JSON.toJSONString(obj);
		return str;
	}

	/**
	 * @Title: json2Obj
	 * @Description: 将JSON字符串转换为对象
	 * @param text  //需要转换的字符串
	 * @param clazz //转换时使用的实体类型
	 * @return
	 */
	public static <X> X json2Obj(String text, Class<X> clazz) {
		X x = JSON.parseObject(text, clazz);
		return x;
	}

	public static <T> T deserializeConfig(String string, Class<T> clazz) {
		if (StringUtils.isBlank(string)) {
			throw new IllegalArgumentException(
					"Blank string cannot be deserialized to class");
		}
		try {
			objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,true);
			T t = objectMapper.readValue(string, clazz);
			return t;
		} catch (IOException e) {
			logger.error(
					"Error deserializing string: " + string, e);
			throw new IllegalArgumentException(
					"Error deserializing string: " + string, e);
		}
	}

	public static <T> T deserialize(String string, Class<T> clazz) {
		if (StringUtils.isBlank(string)) {
			throw new IllegalArgumentException(
					"Blank string cannot be deserialized to class");
		}

		try {
			T t = objectMapper.readValue(string, clazz);
			return t;
		} catch (IOException e) {
			logger.error(
					"Error deserializing string: " + string, e);
			throw new IllegalArgumentException(
					"Error deserializing string: " + string, e);
		}
	}

	public static <T> T deserialize(String string, TypeReference<T> typeReference) {
		if (StringUtils.isBlank(string)) {
			throw new IllegalArgumentException(
					"Blank string cannot be deserialized to class");
		}

		try {
			T t = objectMapper.readValue(string, typeReference);
			return t;
		} catch (IOException e) {
			logger.error(
					"Error deserializing string: " + string, e);
			throw new IllegalArgumentException(
					"Error deserializing string: " + string, e);
		}
	}

	public static String serialize(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Null Object cannot be serialized");
		}

		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("Error serializing object", e);
			throw new IllegalArgumentException("Error serializing object: " + e.getMessage());
		}
	}

	public static boolean isJSONValid(String string) {
		try {
			objectMapper.readTree(string);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static <T> boolean isJSONValid(String string, Class<T> clazz) {
		if (StringUtils.isBlank(string)) {
			return false;
		}

		try {
			objectMapper.readValue(string, clazz);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
