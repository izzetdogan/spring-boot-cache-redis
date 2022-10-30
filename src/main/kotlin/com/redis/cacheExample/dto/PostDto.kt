package com.redis.cacheExample.dto

import com.redis.cacheExample.model.Post
import java.io.Serializable

data class PostDto @JvmOverloads constructor(
    val id: String? = "",
    val title: String,
    val content: String,
):Serializable{
    companion object{
        @JvmStatic
        fun convertToDto(from: Post) : PostDto{
            return PostDto(from.id,from.title,from.content)
        }
    }
}