# spring-boot-cache-redis
An API using redis with spring-boot cache. 
Project starts with 500 data entries to not enter any data

>@Cacheable and @CacheEvict do not run on the same class . To prevent this:  Service class divided to 'PostService' and 'PostCreateService'

### Control cache on terminal (redis-cli)
Redis-server will be running  on docker compose by docker-compose

```
docker exec -it redis-server redis-cli
```
or
```
docker exec -it container_id redis-cli
```
<b>KEYS *</b> = Show  All keys
<b>FLUSHALL </b> = Delete all the keys of all the existing databases


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

> data class may produce NotSeriazebla exception. To avoid this  we can use jacksonObjectModule or objectMapper with kotlinModule

## Dependency
<ul>
<li>spring-boot-starter-data-redis</li>
<li>spring-boot-starter-cache</li>
<li>h2-database</li>
</ul>

## Execute
```
mvn clean install
docker-compose up
 ```

 

