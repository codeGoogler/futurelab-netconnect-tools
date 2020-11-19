package com.net.tools.futurelabnetconnecttools.utils;



import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;

import java.util.List;
import java.util.Map;

public class ConvertUtils {
    private static Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    /**
     * 将对象装换为map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        if(map!=null) {
            beanMap.putAll(map);
        }
        return bean;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean2(Map<String, Object> map,T bean,String name,String value) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        beanMap.put(name,value);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }



    /**
     * 将map装换为javabean对象
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,Class<T> clazz) {
        T bean = null;
        try {
            bean = clazz.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException("实例化对象出错",e);
        }
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }



    /**
     * 将List<Map<String,Object>>转换为List<T>
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
//    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps,Class<T> clazz){
//        List<T> list = Lists.newArrayList();
//        if (maps != null && maps.size() > 0) {
//            Map<String, Object> map = null;
//            T bean = null;
//            for (int i = 0,size = maps.size(); i < size; i++) {
//                map = maps.get(i);
//                try {
//                    bean = clazz.newInstance();
//                } catch (Exception e) {
//                    logger.error("List<Map<String,Object>>转换为List<T>，实体类出错！",e);
//                    throw new AppBusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"服务器出错");
//                }
//                mapToBean(map, bean);
//                list.add(bean);
//            }
//        }
//        return list;
//    }

    /**
     * 获取响应数据里的data
     * @param responseObject
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getData(CommonResponse<Map<String, Object>> responseObject, Class<T> clazz) {
        Map<String, Object> data = responseObject.getData();
        if(data == null) return null;
        return mapToBean(data,clazz);
    }
}
