# spring-boot-cache-redis
An Api using redis with spring-boot cache


## Create RedisConfig

   ```kotlin
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

```

data class may produce NotSeriazebla exception. To avoid this  we can use jacksonObjectModule or objectMapper with kotlinModule

##Dependency
<ul>
<li>spring-boot-starter-data-redis</li>
<li>spring-boot-starter-cache</li>
<li>h2-database</li>
</ul>

##Execute
`mvn clean install
 docker-compose up
 `,
 

