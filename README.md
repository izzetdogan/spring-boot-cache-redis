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
<b>KEYS *</b> = Show  All keys<br/>
<b>FLUSHALL </b> = Delete all the keys of all the existing databases<br/>


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

## API EndPoints
<ul>
<li><b> GET ALL: </b><a>http://localhost:8080/api/v1</a></li>
<li><b> POST: </b><a>http://localhost:8080/api/v1</a></li>
<li><b> PUT:  </b><a>http://localhost:8080/api/v1/id</a></li>
<li><b> DELETE : </b> <a>http://localhost:8080/api/v1/id</a></li>
</ul>


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

 

