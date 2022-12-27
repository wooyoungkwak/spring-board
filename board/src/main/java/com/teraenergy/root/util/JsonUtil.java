package com.teraenergy.root.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teraenergy.root.util.enums.JsonUtilModule;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
public class JsonUtil {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Object 를 JsonNode 로 변환
     *
     * @param object
     * @return
     * @throws TeraException
     */
    public static JsonNode toJsonNode(Object object) throws Exception {
        return toJsonNode(object, JsonUtilModule.NONE);
    }

    /**
     * Object 를 JsonNode 로 변환
     *
     * @param object
     * @param JsonUtilModule - JsonUtilModule
     * @return
     * @throws TeraException
     */
    public static JsonNode toJsonNode(Object object, JsonUtilModule JsonUtilModule) throws Exception {
        try {
            if (object instanceof String) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setDateFormat(dateFormat);
                return objectMapper.readTree((String) object);
            } else
                return toJsonNode(toString(object, JsonUtilModule), JsonUtilModule);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Object 를 JSON 문자열로 변환
     *
     * @param object
     * @return
     * @throws TeraException
     */
    public static <T> String toString(Object object) throws Exception {
        return toString(object, JsonUtilModule.NONE);
    }

    /**
     * Object 를 JSON 문자열로 변환
     *
     * @param object
     * @param JsonUtilModule - JsonUtilModule
     * @return
     * @throws TeraException
     */
    public static <T> String toString(Object object, JsonUtilModule JsonUtilModule) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(dateFormat);
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * JSON 문자열을 Object 로 변환
     *
     * @param json
     * @param clazz
     * @return
     * @throws TeraException
     */
    public static <T> T toObject(String json, Class<T> clazz) throws Exception {
        T object = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(dateFormat);
            object = (T) objectMapper.readValue(json, clazz);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
