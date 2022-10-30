package com.redis.cacheExample.service

import com.redis.cacheExample.dto.PostDto
import com.redis.cacheExample.respository.PostRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {
    @Cacheable(value = ["itemCache"])
    fun getAllPosts(): List<PostDto>{
        Thread.sleep(2000)
        return postRepository.findAll().stream()
            .map { p -> PostDto.convertToDto(p) }.toList()
    }

    fun getPostById(id: String): PostDto{
        val post = postRepository.findByIdOrNull(id) ?: throw RuntimeException("hata")
        return PostDto.convertToDto(post)
    }

    fun getAllPostCount(): Int{
        return postRepository.findAll().count()
    }
}