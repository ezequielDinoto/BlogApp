package android.com.blog.domain

import android.com.blog.core.Resource
import android.com.blog.data.model.Post
import android.content.res.Resources

interface HomeScreenRepo {
   suspend fun getLatestPosts(): Resource<List<Post>>
}