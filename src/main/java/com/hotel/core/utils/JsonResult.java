package com.hotel.core.utils;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private Integer code;

    private Integer count;

    public JsonResult() {

    }

    public JsonResult(Integer code) {
        this.code = code;
    }

    public JsonResult(String msg) {
        this.msg = msg;
    }

    public JsonResult(Integer code, Object data, String msg, Integer count) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.count = count;
    }

    public static JsonResult success(Integer code) {
        return new JsonResult(code);
    }

    public static JsonResult success(String msg) {
        return new JsonResult(msg);
    }

    public static JsonResult build(Integer code) {
        return new JsonResult(code, null, null, null);
    }

    public static JsonResult build(ErrorCodeEnum errorCodeEnum) {
        return new JsonResult(errorCodeEnum.getCode(), null, errorCodeEnum.getDescription(), null);
    }

    public static JsonResult build(Integer code, String msg) {
        return new JsonResult(code, null, msg, null);
    }

    public static JsonResult build(Integer code, Object data) {
        return new JsonResult(code, data, null, null);
    }

    public static JsonResult build(Integer code, Object data, String msg, Integer count) {
        return new JsonResult(code, data, msg, count);
    }

    public static JsonResult build(Integer code,String msg,String username){
        return new JsonResult(code,msg,username,null);
    }

    /**
     * 将json结果集转化为JsonResult对象
     *
     * @param jsonData json数据
     * @param clazz    TaotaoResult中的object类型
     * @return
     */
    public static JsonResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, JsonResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("code").intValue(), obj, jsonNode.get("msg").asText(), jsonNode.get("count").intValue());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static JsonResult format(String json) {
        try {
            return MAPPER.readValue(json, JsonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static JsonResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").intValue(), obj, jsonNode.get("msg").asText(), jsonNode.get("count").intValue());
        } catch (Exception e) {
            return null;
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
