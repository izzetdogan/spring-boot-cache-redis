package com.redis.cacheExample

import com.redis.cacheExample.request.CreatePostRequest
import com.redis.cacheExample.service.PostService
import com.redis.cacheExample.service.PostUpdateService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class CacheExampleApplication(
	private val postUpdateService: PostUpdateService,
	private val postService: PostService
	): CommandLineRunner{
	override fun run(vararg args: String) {
		if(postService.getAllPostCount()<500) {
			for (i in 1..500) {
				postUpdateService.savePost(CreatePostRequest("$i.title", content = "$i.content"))
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<CacheExampleApplication>(*args)
}
