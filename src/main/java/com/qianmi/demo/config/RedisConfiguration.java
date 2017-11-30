package com.qianmi.demo.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport{
    @Bean
    public RedisSerializer jacksonSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    public RedisSerializer stringSerializer(){
        return new StringRedisSerializer();
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(jacksonSerializer());
        template.setKeySerializer(stringSerializer());
        template.setValueSerializer(jacksonSerializer());
        template.setHashKeySerializer(stringSerializer());
        template.setHashValueSerializer(jacksonSerializer());
        return template;
    }
}

