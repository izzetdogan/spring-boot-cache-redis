package com.redis.cacheExample.controller

import com.redis.cacheExample.dto.PostDto
import com.redis.cacheExample.request.CreatePostRequest
import com.redis.cacheExample.service.PostService
import com.redis.cacheExample.service.PostUpdateService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class PostController(
    private val postService: PostService,
    private val postUpdateService: PostUpdateService
) {

    @GetMapping
    fun getAllPosts():ResponseEntity<List<PostDto>>{
        return ResponseEntity(postService.getAllPosts(),HttpStatus.OK)
    }


    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: String): ResponseEntity<PostDto>{
       return ResponseEntity(postService.getPostById(id), HttpStatus.OK)
    }
    @PostMapping
    fun savePost( @RequestBody request: CreatePostRequest): ResponseEntity<PostDto>{
        return ResponseEntity.ok(postUpdateService.savePost(request))
    }

    @PutMapping("/post/{id}")
    fun updatePost(@PathVariable id: String,@RequestBody request: CreatePostRequest): ResponseEntity<PostDto>{
        return ResponseEntity.ok(postUpdateService.updatePost(id,request))
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: String): ResponseEntity<Any>{
        postUpdateService.deletePost(id)
        return ResponseEntity.ok().build()
    }



}