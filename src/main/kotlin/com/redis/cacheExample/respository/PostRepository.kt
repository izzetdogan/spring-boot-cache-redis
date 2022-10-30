package com.redis.cacheExample.respository

import com.redis.cacheExample.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, String> {
}