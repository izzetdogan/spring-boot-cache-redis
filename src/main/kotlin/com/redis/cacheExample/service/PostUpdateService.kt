package com.redis.cacheExample.service

import com.redis.cacheExample.dto.PostDto
import com.redis.cacheExample.request.CreatePostRequest
import com.redis.cacheExample.respository.PostRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class PostUpdateService(private val postRepository: PostRepository) {

    @CacheEvict(value = ["itemCache"], allEntries = true)
    fun savePost(request: CreatePostRequest): PostDto {
        val post = postRepository.save(CreatePostRequest.convert(request))
        return PostDto.convertToDto(post)
    }
    @CacheEvict(value = ["itemCache"])
    fun updatePost(id: String, request: CreatePostRequest): PostDto {
        val post = postRepository.findByIdOrNull(id) ?: throw RuntimeException("hata")
        val updateOne = postRepository.save(post.copy(title = request.title, content = request.content))

        return  PostDto.convertToDto(updateOne)
    }
    @CacheEvict(value = ["itemCache"], allEntries = true)
    fun deletePost(id: String){
        val post = postRepository.findByIdOrNull(id) ?: throw RuntimeException("hata")
        postRepository.delete(post)
    }

}