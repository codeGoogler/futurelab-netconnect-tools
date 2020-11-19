package com.net.tools.futurelabnetconnecttools.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.net.tools.futurelabnetconnecttools.common.FastJsonRedisSerializer;
import com.net.tools.futurelabnetconnecttools.common.exception.ExceptionProcessUtils;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponseFactory;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.entity.Student;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * redis   key失效监听设置
 * @author Mr.tang
 * @version 1.0
 */
/**
 * redis配置类
 **/
@Configuration
@EnableCaching//开启注解式缓存
//继承CachingConfigurerSupport，为了自定义生成KEY的策略。可以不继承。
public class RedisListenerConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public KeyGenerator keyGenerator()
    {
        return (o,method,objects)->
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(o.getClass().getName());
            stringBuilder.append(method.getName());
            for (Object obj : objects)
            {
                stringBuilder.append(obj.toString());
            }
            return stringBuilder.toString();
        };
    }
    /**
     *
     * Description: redisTemplate序列化
     * @param factory
     * @author SunJ
     * @date 2019/10/22
     */
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory factory)
    {
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(factory);
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        /*设置value值的序列化*/
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        /*设置key的序列化*/
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
