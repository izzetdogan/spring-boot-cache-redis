package com.redis.cacheExample.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration


@Configuration
class RedisConfig{
    @Bean
    fun cacheConfiguration(): RedisCacheConfiguration {
        val mapper = jacksonObjectMapper()
            .registerModule(JavaTimeModule())
            .activateDefaultTyping(BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Any::class.java)
                .build(), ObjectMapper.DefaultTyping.EVERYTHING)
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .disableCachingNullValues()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair
                    .fromSerializer(GenericJackson2JsonRedisSerializer(mapper))
            )
    }

}



/*
@Bean
fun redisConnectFactory():LettuceConnectionFactory{
    return LettuceConnectionFactory(RedisStandaloneConfiguration("localhost",6379))
}

@Bean
fun redisTemplate(): RedisTemplate<Any, Any> {
    val redisTemplate = RedisTemplate<Any, Any>()
    redisTemplate.setConnectionFactory(redisConnectFactory())
    return redisTemplate
}


@Bean
fun redisCacheManager(lettuceConnectionFactory: LettuceConnectionFactory): RedisCacheManager{

    val onn = jacksonObjectMapper()
        .registerModule(JavaTimeModule())
        .activateDefaultTyping(BasicPolymorphicTypeValidator.builder()
            .allowIfBaseType(Any::class.java)
            .build(), ObjectMapper.DefaultTyping.EVERYTHING)

    val configuration= RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
        .entryTtl(Duration.ofMinutes(12))
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer(onn)))

    configuration.usePrefix()

    return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory)
        .cacheDefaults(configuration).build()
}

 */

