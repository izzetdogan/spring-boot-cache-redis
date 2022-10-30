package com.redis.cacheExample.request

import com.redis.cacheExample.model.Post

data class CreatePostRequest(
    val title:String, val content: String
)
{
    companion object
    {
        @JvmStatic
        fun convert(from:CreatePostRequest): Post{
            return Post(title = from.title, content = from.content)
        }
    }
}
